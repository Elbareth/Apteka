package com.example.apteka.assembler;

import com.example.apteka.dto.MedicineDTO;
import com.example.apteka.entity.MedicineEntity;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MedicineAssemblerTest {

    private MedicineEntity medicineEntity;
    private MedicineDTO medicineDTO;
    private List<MedicineEntity> listEntity;
    private List<MedicineDTO> listDto;
    @Autowired
    private MedicineAssembler medicineAssembler;
    @Autowired
    private Flyway flyway;

    @Before
    public void onInit(){
        medicineEntity = new MedicineEntity("Ibuprom", LocalDate.of(2020,06,30), "...",7.50f);
        medicineDTO = new MedicineDTO("Ibuprom", LocalDate.of(2020,06,30), "...",7.50f);
        listEntity = new ArrayList<>();
        listDto = new ArrayList<>();
        listEntity.add(medicineEntity);
        listDto.add(medicineDTO);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void toDto() {
        Assertions.assertThat(medicineAssembler.toDto(medicineEntity)).isEqualTo(medicineDTO);
    }

    @Test
    public void toDto1() {
        Assertions.assertThat(medicineAssembler.toDto(listEntity)).isEqualTo(listDto);
    }

    @Test
    public void toEntity() {
        Assertions.assertThat(medicineAssembler.toEntity(medicineDTO)).isEqualTo(medicineEntity);
    }

    @Test
    public void toEntity1() {
        Assertions.assertThat(medicineAssembler.toEntity(listDto)).isEqualTo(listEntity);
    }
}