package com.example.apteka.assembler;

import com.example.apteka.dto.MedicineDTO;
import com.example.apteka.entity.MedicineEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedicineAssembler {

    public MedicineDTO toDto(MedicineEntity medicineEntity){
        return new MedicineDTO(
                medicineEntity.getName(),
                medicineEntity.getDateOfUse(),
                medicineEntity.getDescription(),
                medicineEntity.getPrice());
    }
    public List<MedicineDTO> toDto(List<MedicineEntity> medicineEntities){
        List<MedicineDTO> listOfMedicineDto = new ArrayList<>();
        medicineEntities.forEach(param ->{
            listOfMedicineDto.add(toDto(param));
        });
        return listOfMedicineDto;
    }

    public MedicineEntity toEntity(MedicineDTO medicineDto){
        return new MedicineEntity(
                medicineDto.getName(),
                medicineDto.getDateOfUse(),
                medicineDto.getDescription(),
                medicineDto.getPrice());
    }
    public List<MedicineEntity> toEntity(List<MedicineDTO> medicineDTOS){
        List<MedicineEntity> listOfMedicineEntity = new ArrayList<>();
        medicineDTOS.forEach(param ->{
            listOfMedicineEntity.add(toEntity(param));
        });
        return listOfMedicineEntity;
    }

    public void updateEntity(MedicineEntity medicineEntity, MedicineDTO medicineDTO){
        medicineEntity.setName(medicineDTO.getName());
        medicineEntity.setDateOfUse(medicineDTO.getDateOfUse());
        medicineEntity.setDescription(medicineDTO.getDescription());
        medicineEntity.setPrice(medicineDTO.getPrice());
    }
}
