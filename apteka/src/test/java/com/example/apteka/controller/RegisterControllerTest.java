package com.example.apteka.controller;

import com.example.apteka.dto.UserDTO;
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
public class RegisterControllerTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private RegisterController registerController;
    private UserDTO userDTO;

    @Before
    public void onInit(){
        userDTO = new UserDTO("UserXYZ","UserXYZ","UserXYZ","Admin","Admin");
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void create() {
        Assertions.assertThat(registerController.create(userDTO)).isEqualTo(userDTO);
    }
}