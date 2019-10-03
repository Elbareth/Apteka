package com.example.apteka.controller;

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

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MedicineControllerTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private MedicineController medicineController;
    @Autowired
    private HttpSession session;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MedicineAssembler medicineAssembler;
    private MedicineDTO medicineDTO;

    @Before
    public void onInit(){
        medicineDTO = new MedicineDTO("Ibuprom", LocalDate.of(2020,06,30), "...",7.50f);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getById() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(medicineController.getById(1)).isEqualTo(medicineAssembler.toDto(medicineRepository.findById(1).get()));
    }

    @Test
    public void getByIdUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(medicineController.getById(1)).isEqualTo(medicineAssembler.toDto(medicineRepository.findById(1).get()));
    }

    @Test
    public void getByIdAnonim() {
        try{
            medicineController.getById(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }

    }

    @Test
    public void getAll() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(medicineController.getAll()).isEqualTo(medicineAssembler.toDto(medicineRepository.findAll()));
    }

    @Test
    public void getAllUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(medicineController.getAll()).isEqualTo(medicineAssembler.toDto(medicineRepository.findAll()));
    }

    @Test
    public void getAllAnonim() {
        try{
            medicineController.getAll();
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void create() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(medicineController.create(medicineDTO)).isEqualTo(medicineDTO);
    }

    @Test
    public void createUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            medicineController.create(medicineDTO);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void createAnonim() {
        try{
            medicineController.create(medicineDTO);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void delete() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Integer sizeBefore = medicineRepository.findAll().size();
        medicineController.delete(2);
        Integer sizeAfter = medicineRepository.findAll().size();
        Assertions.assertThat(sizeAfter).isEqualTo(sizeBefore - 1);
    }

    @Test
    public void deleteUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            medicineController.delete(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void deleteAnonim() {
        try{
            medicineController.delete(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void update() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(medicineController.update(2,medicineDTO)).isEqualTo(medicineDTO);
    }

    @Test
    public void updateUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            medicineController.update(1,medicineDTO);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void updateAnonim() {
        try{
            medicineController.update(1,medicineDTO);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void sort() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(medicineController.sort()).isEqualTo(medicineAssembler.toDto(medicineRepository.findAllByOrderByNameAsc()));
    }

    @Test
    public void sortUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(medicineController.sort()).isEqualTo(medicineAssembler.toDto(medicineRepository.findAllByOrderByNameAsc()));
    }

    @Test
    public void sortAnonim() {
        try{
            medicineController.sort();
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void pagination() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(medicineController.pagination(0)).isEqualTo(medicineAssembler.toDto(medicineRepository.findAll()));
    }

    @Test
    public void paginationUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(medicineController.pagination(0)).isEqualTo(medicineAssembler.toDto(medicineRepository.findAll()));
    }

    @Test
    public void paginationAnonim() {
        try{
            medicineController.pagination(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void findByName() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(medicineController.findByName("Ibuprom")).isEqualTo(medicineAssembler.toDto(medicineRepository.findByName("Ibuprom")));
    }

    @Test
    public void findByNameUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(medicineController.findByName("Ibuprom")).isEqualTo(medicineAssembler.toDto(medicineRepository.findByName("Ibuprom")));
    }

    @Test
    public void findByNameAnonim() {
        try{
            medicineController.findByName("Admin");
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }
}