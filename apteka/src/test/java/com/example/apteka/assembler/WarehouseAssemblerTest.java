package com.example.apteka.assembler;

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


@SpringBootTest
@RunWith(SpringRunner.class)
public class WarehouseAssemblerTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private WarehouseAssembler warehouseAssembler;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    private WarehouseDTO warehouseDTO;
    private WarehouseEntity warehouseEntity;
    private List<WarehouseDTO> listDto;
    private List<WarehouseEntity> listEntity;

    @Before
    public void onInit(){
        warehouseDTO = new WarehouseDTO("Moja apteka",1,2);
        warehouseEntity = new WarehouseEntity("Moja apteka", medicineRepository.findById(1).get(),userRepository.findById(2).get());
        listDto = new ArrayList<>();
        listDto.add(warehouseDTO);
        listEntity = new ArrayList<>();
        listEntity.add(warehouseEntity);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void toDto() {
        Assertions.assertThat(warehouseAssembler.toDto(warehouseEntity)).isEqualTo(warehouseDTO);
    }

    @Test
    public void toDto1() {
        Assertions.assertThat(warehouseAssembler.toDto(listEntity)).isEqualTo(listDto);
    }

    @Test
    public void toEntity() {
        Assertions.assertThat(warehouseAssembler.toEntity(warehouseDTO)).isEqualTo(warehouseEntity);
    }

    @Test
    public void toEntity1() {
        Assertions.assertThat(warehouseAssembler.toEntity(listDto)).isEqualTo(listEntity);
    }

}