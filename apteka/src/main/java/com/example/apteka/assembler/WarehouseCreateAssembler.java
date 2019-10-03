package com.example.apteka.assembler;

import com.example.apteka.dto.WarehouseCreateDTO;
import com.example.apteka.entity.WarehouseEntity;
import com.example.apteka.repository.MedicineRepository;
import com.example.apteka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WarehouseCreateAssembler {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MedicineAssembler medicineAssembler;
    @Autowired
    private UserAssembler userAssembler;

    public WarehouseCreateDTO toDto(WarehouseEntity warehouseEntity){
        List<Integer> medicineList = new ArrayList<>();
        medicineList.add(warehouseEntity.getListOfMedicines().getId());
        List<Integer> userList = new ArrayList<>();
        userList.add(warehouseEntity.getListOfUsers().getId());
        return new WarehouseCreateDTO(
                warehouseEntity.getName(),
                medicineList,
                userList
        );
    }

    public List<WarehouseCreateDTO> toDto(List<WarehouseEntity> warehouseEntities){
        List<WarehouseCreateDTO> listOfWarehouseDto = new ArrayList<>();
        warehouseEntities.forEach(param ->{
            listOfWarehouseDto.add(toDto(param));
        });
        return listOfWarehouseDto;
    }

    public List<WarehouseEntity> toEntity(WarehouseCreateDTO warehouseDTO){
        List<WarehouseEntity> tmpList = new ArrayList<>();
        warehouseDTO.getListOfMedicines().forEach( medicine -> {
            warehouseDTO.getListOfUsers().forEach( user -> {
                WarehouseEntity entity = new WarehouseEntity(
                        warehouseDTO.getName(),
                        medicineRepository.findById(medicine).get(),
                        userRepository.findById(user).get());
                tmpList.add(entity);
            });
        });
        return tmpList;
    }

    public List<List<WarehouseEntity>> toEntity(List<WarehouseCreateDTO> warehouseDTOS){
        List<List<WarehouseEntity>> listOfWarehouseEntity = new ArrayList<>();
        warehouseDTOS.forEach(param ->{
            listOfWarehouseEntity.add(toEntity(param));
        });
        return listOfWarehouseEntity;
    }
}
