package com.example.apteka.assembler;

import com.example.apteka.dto.UserDTO;
import com.example.apteka.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserAssemblerTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private UserAssembler userAssembler;
    private UserEntity userEntity;
    private UserDTO userDTO;
    private List<UserEntity> listEntity;
    private List<UserDTO> listDTO;

    @Before
    public void onInit(){
        userDTO = new UserDTO("Admin","Admin","Admin","Admin","Admin");
        userEntity = new UserEntity("Admin","Admin","Admin","Admin","Admin");
        listEntity = new ArrayList<>();
        listEntity.add(userEntity);
        listDTO = new ArrayList<>();
        listDTO.add(userDTO);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void toDto() {
        Assertions.assertThat(userAssembler.toDto(userEntity)).isEqualTo(userDTO);
    }

    @Test
    public void toDto1() {
        Assertions.assertThat(userAssembler.toDto(listEntity)).isEqualTo(listDTO);
    }

    @Test
    public void toEntity() {
        Assertions.assertThat(userAssembler.toEntity(userDTO)).isEqualTo(userEntity);
    }

    @Test
    public void toEntity1() {
        Assertions.assertThat(userAssembler.toEntity(listDTO)).isEqualTo(listEntity);
    }
}