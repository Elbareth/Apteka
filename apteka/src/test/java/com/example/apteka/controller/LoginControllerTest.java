package com.example.apteka.controller;

import com.example.apteka.assembler.UserAssembler;
import com.example.apteka.dto.LoginDTO;
import com.example.apteka.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginControllerTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private LoginController loginController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAssembler userAssembler;
    private LoginDTO loginDTO;

    @Before
    public void onInit(){
        loginDTO = new LoginDTO("Admin","Admin");
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void get() {
        Assertions.assertThat(loginController.get(loginDTO))
                .isEqualTo(userAssembler.toDto(userRepository.findByLogin(loginDTO.getLogin())));
    }
}