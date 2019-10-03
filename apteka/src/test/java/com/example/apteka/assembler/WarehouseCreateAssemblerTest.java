package com.example.apteka.assembler;

import com.example.apteka.dto.WarehouseCreateDTO;
import com.example.apteka.dto.WarehouseDTO;
import com.example.apteka.entity.WarehouseEntity;
import com.example.apteka.repository.MedicineRepository;
import com.example.apteka.repository.UserRepository;
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
public class WarehouseCreateAssemblerTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private WarehouseCreateAssembler warehouseCreateAssembler;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    private WarehouseCreateDTO warehouseDTO;
    private WarehouseEntity warehouseEntity;
    private List<WarehouseCreateDTO> listDto;
    private List<WarehouseEntity> listEntity;
    private List<List<WarehouseEntity>> listListEntity;

    @Before
    public void onInit(){
        List<Integer> num = new ArrayList<>();
        num.add(1);
        warehouseDTO = new WarehouseCreateDTO("Moja apteka",num,num);
        warehouseEntity = new WarehouseEntity("Moja apteka", medicineRepository.findById(1).get(),userRepository.findById(1).get());
        listDto = new ArrayList<>();
        listDto.add(warehouseDTO);
        listEntity = new ArrayList<>();
        listEntity.add(warehouseEntity);
        listListEntity = new ArrayList<>();
        listListEntity.add(listEntity);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void toDto() {
        Assertions.assertThat(warehouseCreateAssembler.toDto(warehouseEntity)).isEqualTo(warehouseDTO);
    }

    @Test
    public void toDto1() {
        Assertions.assertThat(warehouseCreateAssembler.toDto(listEntity)).isEqualTo(listDto);
    }

    @Test
    public void toEntity() {
        Assertions.assertThat(warehouseCreateAssembler.toEntity(warehouseDTO)).isEqualTo(listEntity);
    }

    @Test
    public void toEntity1() {
        Assertions.assertThat(warehouseCreateAssembler.toEntity(listDto)).isEqualTo(listListEntity);
    }
}