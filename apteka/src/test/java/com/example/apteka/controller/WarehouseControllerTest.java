package com.example.apteka.controller;

import com.example.apteka.assembler.WarehouseAssembler;
import com.example.apteka.assembler.WarehouseCreateAssembler;
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

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class WarehouseControllerTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private HttpSession session;
    @Autowired
    private WarehouseController warehouseController;
    @Autowired
    private WarehouseAssembler warehouseAssembler;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseCreateAssembler warehouseCreateAssembler;
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
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(warehouseController.getById(1)).isEqualTo(warehouseAssembler.toDto(warehouseRepository.findById(1).get()));
    }

    @Test
    public void getByIdUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(warehouseController.getById(1)).isEqualTo(warehouseAssembler.toDto(warehouseRepository.findById(1).get()));
    }

    @Test
    public void getByIdAnonim() {
        try{
            warehouseController.getById(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void getAll() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(warehouseController.getAll()).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findAll()));
    }

    @Test
    public void getAllUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(warehouseController.getAll()).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findAll()));
    }

    @Test
    public void getAllAnonim() {
        try{
            warehouseController.getAll();
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void create() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(warehouseController.create(warehouseCreateDTO)).isEqualTo(warehouseCreateDTO);
    }

    @Test
    public void createUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            warehouseController.create(warehouseCreateDTO);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void createAnonim() {
        try{
            warehouseController.create(warehouseCreateDTO);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void update() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(warehouseController.update(1,warehouseDTO)).isEqualTo(warehouseDTO);
    }

    @Test
    public void updateUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            warehouseController.update(1,warehouseDTO);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void updateAnonim() {
        try{
            warehouseController.update(1,warehouseDTO);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void delete() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Integer sizeBefore = warehouseRepository.findAll().size();
        warehouseController.delete(1);
        Integer sizeAfter = warehouseRepository.findAll().size();
        Assertions.assertThat(sizeBefore-1).isEqualTo(sizeAfter);
    }

    @Test
    public void deleteUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            warehouseController.delete(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void deleteAnonim() {
        try{
            warehouseController.delete(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void sort() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(warehouseController.sort()).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findAllByOrderByNameAsc()));
    }

    @Test
    public void sortUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(warehouseController.sort()).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findAllByOrderByNameAsc()));
    }

    @Test
    public void sortAnonim() {
        try{
            warehouseController.sort();
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void pagination() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(warehouseController.pagination(0)).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findAll()));
    }

    @Test
    public void paginationUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(warehouseController.pagination(0)).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findAll()));
    }

    @Test
    public void paginationAnonim() {
        try{
            warehouseController.pagination(0);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void findByName() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(warehouseController.findByName("Wesola apteka")).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findByName("Wesola apteka")));
    }

    @Test
    public void findByNameUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        Assertions.assertThat(warehouseController.findByName("Wesola apteka")).isEqualTo(warehouseCreateAssembler.toDto(warehouseRepository.findByName("Wesola apteka")));
    }

    @Test
    public void findByNameAnonim() {
        try{
            warehouseController.findByName("Wesola apteka");
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }
}