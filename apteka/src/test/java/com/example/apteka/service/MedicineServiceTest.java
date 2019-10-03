package com.example.apteka.service;

import com.example.apteka.assembler.MedicineAssembler;
import com.example.apteka.dto.MedicineDTO;
import com.example.apteka.repository.MedicineRepository;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MedicineServiceTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private MedicineService medicineService;
    private MedicineDTO medicineDTO;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MedicineAssembler medicineAssembler;

    @Before
    public void onInit(){
        medicineDTO = new MedicineDTO("Ibuprom", LocalDate.of(2020,06,30), "...",7.50f);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getById() {
        Assertions.assertThat(medicineService.getById(1)).isEqualTo(medicineAssembler.toDto(medicineRepository.findById(1).get()));
    }

    @Test
    public void getByIdError() {
        try{
            medicineService.getById(100);
        }
        catch(EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no entity with this id");
        }
    }

    @Test
    public void getAll() {
        Assertions.assertThat(medicineService.getAll()).isEqualTo(medicineAssembler.toDto(medicineRepository.findAll()));
    }

    @Test
    public void create() {
        Assertions.assertThat(medicineService.create(medicineDTO)).isEqualTo(medicineDTO);
    }

    @Test
    public void createEmpty() {
        try{
            MedicineDTO med = new MedicineDTO();
            medicineService.create(med);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Please fill all gap");
        }
    }

    @Test
    public void update() {
        Assertions.assertThat(medicineService.update(1,medicineDTO)).isEqualTo(medicineDTO);
    }

    @Test
    public void updateWrongId() {
        try{
            medicineService.update(100,medicineDTO);
        }
        catch(EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no entity with this id");
        }
    }

    @Test
    public void updateEmpty() {
        try{
            MedicineDTO med = new MedicineDTO();
            medicineService.update(1,med);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Please fill all gap");
        }
    }

    @Test
    public void delete() {
        Integer sizeBefore = medicineRepository.findAll().size();
        medicineService.delete(1);
        Integer sizeAfter = medicineRepository.findAll().size();
        Assertions.assertThat(sizeBefore-1).isEqualTo(sizeAfter);
    }

    @Test
    public void deleteWrongId() {
        try{
            medicineService.delete(1000);
        }
        catch(EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no entity with this id");
        }
    }

    @Test
    public void sortByName() {
        Assertions.assertThat(medicineService.sortByName()).isEqualTo(medicineAssembler.toDto(medicineRepository.findAllByOrderByNameAsc()));
    }

    @Test
    public void pagination() {
        Assertions.assertThat(medicineService.pagination(0)).isEqualTo(medicineAssembler.toDto(medicineRepository.findAll()));
    }

    @Test
    public void findByName() {
        Assertions.assertThat(medicineService.findByName("Ibuprom")).isEqualTo(medicineAssembler.toDto(medicineRepository.findByName("Ibuprom")));
    }

    @Test
    public void findByNameWrong() {
        try{
            medicineService.findByName("xyz");
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no medicine with such name");
        }
    }
}