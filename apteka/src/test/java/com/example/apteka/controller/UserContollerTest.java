package com.example.apteka.controller;

import com.example.apteka.assembler.UserAssembler;
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

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserContollerTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private HttpSession session;
    @Autowired
    private UserContoller userContoller;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAssembler userAssembler;
    private UserDTO userDTO;

    @Before
    public void onInit(){
        userDTO = new UserDTO("Admin","Admin","Admin","Admin","Admin");
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void delete() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Integer sizeBefore = userRepository.findAll().size();
        userContoller.delete(1);
        Integer sizeAfter = userRepository.findAll().size();
        Assertions.assertThat(sizeBefore-1).isEqualTo(sizeAfter);
    }

    @Test
    public void deleteUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            userContoller.delete(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void deleteAnonim() {
        try{
            userContoller.delete(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void getById() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(userContoller.getById(1)).isEqualTo(userAssembler.toDto(userRepository.findById(1).get()));
    }

    @Test
    public void getByIdUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            userContoller.getById(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void getByIdAnonim() {
        try{
            userContoller.getById(1);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void getAll() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(userContoller.getAll()).isEqualTo(userAssembler.toDto(userRepository.findAll()));
    }

    @Test
    public void getAllUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            userContoller.getAll();
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void getAllAnonim() {
        try{
            userContoller.getAll();
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void update() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(userContoller.update(userDTO,1)).isEqualTo(userDTO);
    }

    @Test
    public void updateUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            userContoller.update(userDTO,2);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You can't update another user");
        }
    }

    @Test
    public void updateAnonim() {
        try{
            userContoller.getAll();
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void sort() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(userContoller.sort()).isEqualTo(userAssembler.toDto(userRepository.findAllByOrderByNameAsc()));
    }

    @Test
    public void sortUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            userContoller.sort();
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void sortAnonim() {
        try{
            userContoller.sort();
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void pagination() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        List<UserDTO> listOf20 = new ArrayList<>();
        for(int i=0;i<20;i++){
            listOf20.add(userAssembler.toDto(userRepository.findAll().get(i)));
        }
        Assertions.assertThat(userContoller.pagination(0)).isEqualTo(listOf20);
    }

    @Test
    public void paginationUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            userContoller.pagination(0);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void paginationAnonim() {
        try{
            userContoller.pagination(0);
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void findByLogin() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(userContoller.findByLogin("Admin")).isEqualTo(userAssembler.toDto(userRepository.findByLogin("Admin")));
    }

    @Test
    public void findByLoginUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            userContoller.findByLogin("Admin");
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void findByLoginAnonim() {
        try{
            userContoller.findByLogin("Admin");
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }

    @Test
    public void findBySurname() {
        session.setAttribute("login","Admin");
        session.setAttribute("role","Admin");
        Assertions.assertThat(userContoller.findBySurname("Admin")).isEqualTo(userAssembler.toDto(userRepository.findBySurname("Admin")));
    }

    @Test
    public void findBySurnameUser() {
        session.setAttribute("login","User");
        session.setAttribute("role","User");
        try{
            userContoller.findBySurname("Admin");
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Sorry. You haven't got permission to this activity");
        }
    }

    @Test
    public void findBySurnameAnonim() {
        try{
            userContoller.findBySurname("Admin");
        }
        catch(RuntimeException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("You are not logged");
        }
    }
}