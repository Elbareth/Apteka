package com.example.apteka.service;

import com.example.apteka.assembler.UserAssembler;
import com.example.apteka.dto.LoginDTO;
import com.example.apteka.dto.UserDTO;
import com.example.apteka.repository.UserRepository;
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
public class UserServiceTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAssembler userAssembler;
    @Autowired
    private UserRepository userRepository;
    private UserDTO userDTO;

    @Before
    public void onInit(){
        userDTO = new UserDTO("UserXYZ","UserXYZ","UserXYZ","Admin","Admin");
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void getById() {
        Assertions.assertThat(userService.getById(1)).isEqualTo(userAssembler.toDto(userRepository.findById(1).get()));
    }

    @Test
    public void getByIdWrong() {
        try{
            userService.getById(1000);
        }
        catch(EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no entity with this id");
        }
    }

    @Test
    public void getByLogin() {
        Assertions.assertThat(userService.getByLogin("Admin","Admin")).isEqualTo(userAssembler.toDto(userRepository.findByLogin("Admin")));
    }

    @Test
    public void getByNonValid() {
        try{
            userService.getByLogin("Admin","xyz");
        }
        catch (EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Login ar password You enter is not valid");
        }
    }

    @Test
    public void getByLoginEmpty() {
        try{
            userService.getByLogin(null,null);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Gap can't be empty");
        }
    }

    @Test
    public void getAll() {
        Assertions.assertThat(userService.getAll()).isEqualTo(userAssembler.toDto(userRepository.findAll()));
    }

    @Test
    public void create() {
        Assertions.assertThat(userService.create(userDTO)).isEqualTo(userDTO);
    }

    @Test
    public void createEmpty() {
        try{
            UserDTO use = new UserDTO();
            userService.create(use);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo(null);
        }
    }

    @Test
    public void update() {
        userDTO = new UserDTO("Admin","Admin","Admin","Admin","Admin");
        Assertions.assertThat(userService.update(1,userDTO)).isEqualTo(userDTO);
    }

    @Test
    public void updateEmpty() {
        try{
            UserDTO use = new UserDTO();
            userService.update(1,use);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Please fill all gaps");
        }
    }

    @Test
    public void updateWrongId() {
        try{
            userService.update(1000,userDTO);
        }
        catch(EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no entity with this id");
        }
    }

    @Test
    public void delete() {
        Integer sizeBefore = userRepository.findAll().size();
        userService.delete(1);
        Integer sizeAfter = userRepository.findAll().size();
        Assertions.assertThat(sizeBefore-1).isEqualTo(sizeAfter);
    }

    @Test
    public void deleteWrongId() {
        try{
            userService.delete(1000);
        }
        catch(EntityNotFoundException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no entity with this id");
        }
    }

    @Test
    public void sortByName() {
        Assertions.assertThat(userService.sortByName()).isEqualTo(userAssembler.toDto(userRepository.findAllByOrderByNameAsc()));
    }

    @Test
    public void pagination() {
        List<UserDTO> listOf20 = new ArrayList<>();
        for(int i=0;i<20;i++){
            listOf20.add(userAssembler.toDto(userRepository.findAll().get(i)));
        }
        Assertions.assertThat(userService.pagination(0)).isEqualTo(listOf20);
    }

    @Test
    public void findByLogin() {
        Assertions.assertThat(userService.findByLogin("Admin")).isEqualTo(userAssembler.toDto(userRepository.findByLogin("Admin")));
    }

    @Test
    public void findByLoginWrong() {
        try{
            userService.findByLogin("Admin");
        }
        catch(RuntimeException e ){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no user with such login");
        }
    }

    @Test
    public void findBySurname() {
        Assertions.assertThat(userService.findBySurname("Admin")).isEqualTo(userAssembler.toDto(userRepository.findBySurname("Admin")));
    }

    @Test
    public void findBySurnameWrong() {
        try{
            userService.findByLogin("Admin");
        }
        catch(RuntimeException e ){
            Assertions.assertThat(e.getMessage()).isEqualTo("There is no user with such surname");
        }
    }
}