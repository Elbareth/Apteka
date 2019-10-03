package com.example.apteka.service;

import com.example.apteka.assembler.WarehouseAssembler;
import com.example.apteka.assembler.WarehouseCreateAssembler;
import com.example.apteka.controller.WarehouseController;
import com.example.apteka.dto.WarehouseCreateDTO;
import com.example.apteka.dto.WarehouseDTO;
import com.example.apteka.repository.WarehouseRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class WarehouseServiceTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseAssembler warehouseAssembler;
    @Autowired
    private WarehouseCreateAssembler warehouseCreateAssembler;
    @Autowired
    private WarehouseService warehouseService;
    private WarehouseDTO warehouseDTO;
    private WarehouseCreateDTO warehouseCreateDTO;

    @Before
    public void onInit(){
        warehouseDTO = new WarehouseDTO("Moja apteka",1,1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        warehouseCreateDTO = new WarehouseCreateDTO("Moja apteka",list,list);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getById() {
        Assertions.assertThat(warehouseService.getById(1)).isEqualTo(warehouseAssembler.toDto(warehouseRepository.findById(1).get()));
    }

    @Test
    public void getByIdWrong() {
        try{
            warehouseService.getById(1000);
        }
        catch(EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no entity with this id");
        }
    }

    @Test
    public void getAll() {
        Assertions.assertThat(warehouseService.getAll()).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findAll()));
    }

    @Test
    public void create() {
        Assertions.assertThat(warehouseService.create(warehouseCreateDTO)).isEqualTo(warehouseCreateDTO);
    }

    @Test
    public void createEmpty() {
        try{
            WarehouseCreateDTO war = new WarehouseCreateDTO();
            warehouseService.create(war);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Please fill all gap");
        }
    }

    @Test
    public void update() {
        Assertions.assertThat(warehouseService.update(1,warehouseDTO)).isEqualTo(warehouseDTO);
    }

    @Test
    public void updateEmpty() {
        try{
            WarehouseDTO war = new WarehouseDTO();
            warehouseService.update(1,war);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Please fill all gap");
        }
    }

    @Test
    public void updateWrongId() {
        try{
            warehouseService.update(1000,warehouseDTO);
        }
        catch(EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no entity with this id");
        }
    }

    @Test
    public void delete() {
        Integer sizeBefore = warehouseRepository.findAll().size();
        warehouseService.delete(1);
        Integer sizeAfter = warehouseRepository.findAll().size();
        Assertions.assertThat(sizeBefore-1).isEqualTo(sizeAfter);
    }

    @Test
    public void deleteWrongId() {
        try{
            warehouseService.delete(1000);
        }
        catch(EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no entity with this id");
        }
    }

    @Test
    public void sortByName() {
        Assertions.assertThat(warehouseService.sortByName()).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findAllByOrderByNameAsc()));
    }

    @Test
    public void pagination() {
        Assertions.assertThat(warehouseService.pagination(0)).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findAll()));
    }

    @Test
    public void findByName() {
        Assertions.assertThat(warehouseService.findByName("Wesola apteka")).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findByName("Wesola apteka")));
    }

    @Test
    public void findByNameWrongId() {
        try{
            warehouseService.findByName("xxx");
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no warehouse with such name");
        }
    }
}