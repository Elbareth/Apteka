package com.example.apteka.assembler;

import com.example.apteka.dto.WarehouseDTO;
import com.example.apteka.entity.WarehouseEntity;
import com.example.apteka.repository.MedicineRepository;
import com.example.apteka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WarehouseAssembler {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private UserRepository userRepository;

    public WarehouseDTO toDto(WarehouseEntity warehouseEntity){
        return new WarehouseDTO(
                warehouseEntity.getName(),
                warehouseEntity.getListOfMedicines().getId(),
                warehouseEntity.getListOfUsers().getId()
        );
    }

    public List<WarehouseDTO> toDto(List<WarehouseEntity> warehouseEntities){
        List<WarehouseDTO> listOfWarehouseDto = new ArrayList<>();
        warehouseEntities.forEach(param ->{
            listOfWarehouseDto.add(toDto(param));
        });
        return listOfWarehouseDto;
    }

    public WarehouseEntity toEntity(WarehouseDTO warehouseDTO){
        return new WarehouseEntity(
                warehouseDTO.getName(),
                medicineRepository.findById(warehouseDTO.getListOfMedicines()).get(),
                userRepository.findById(warehouseDTO.getListOfUsers()).get()
        );
    }

    public List<WarehouseEntity> toEntity(List<WarehouseDTO> warehouseDTOS){
        List<WarehouseEntity> listOfWarehouseEntity = new ArrayList<>();
        warehouseDTOS.forEach(param ->{
            listOfWarehouseEntity.add(toEntity(param));
        });
        return listOfWarehouseEntity;
    }

    public void updateEntity(WarehouseEntity warehouseEntity, WarehouseDTO warehouseDTO){
        warehouseEntity.setName(warehouseDTO.getName());
        warehouseEntity.setListOfMedicines(medicineRepository.findById(warehouseDTO.getListOfMedicines()).get());
        warehouseEntity.setListOfUsers(userRepository.findById(warehouseDTO.getListOfUsers()).get());
    }
}
