package com.example.apteka.service;

import com.example.apteka.assembler.WarehouseAssembler;
import com.example.apteka.assembler.WarehouseCreateAssembler;
import com.example.apteka.dto.WarehouseCreateDTO;
import com.example.apteka.dto.WarehouseDTO;
import com.example.apteka.entity.WarehouseEntity;
import com.example.apteka.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseAssembler warehouseAssembler;
    @Autowired
    private WarehouseCreateAssembler warehouseCreateAssembler;

    public WarehouseDTO getById(Integer id){
        if(!warehouseRepository.existsById(id)){
            throw new EntityNotFoundException("There is no entity with this id");
        }
        else return warehouseAssembler.toDto(warehouseRepository.getOne(id));
    }

    public List<WarehouseCreateDTO> getAll(){
        return warehouseCreateAssembler.toDto(warehouseRepository.findAll());
    }
    public WarehouseCreateDTO create(WarehouseCreateDTO warehouseDTO){
        if(emptyGap(warehouseDTO)) throw new RuntimeException("Please fill all gap");
        else{
            List<WarehouseEntity> entities = new ArrayList<>();
            warehouseCreateAssembler.toEntity(warehouseDTO).forEach( it ->{
                entities.add(warehouseRepository.save(it));
            });
            return warehouseDTO;
        }
    }
    @Transactional
    public WarehouseDTO update(Integer id, WarehouseDTO warehouseDTO){
        if(!warehouseRepository.existsById(id)){
            throw new EntityNotFoundException("There is no entity with this id");
        }
        else if(emptyGap(warehouseDTO)) throw new RuntimeException("Please fill all gap");
        else {
            WarehouseEntity warehouseEntity = warehouseRepository.getOne(id);
            warehouseAssembler.updateEntity(warehouseEntity, warehouseDTO);
            return warehouseDTO;
        }
    }
    public void delete(Integer id){
        if(!warehouseRepository.existsById(id)){
            throw new EntityNotFoundException("There is no entity with this id");
        }
        else warehouseRepository.deleteById(id);
    }
    public List<WarehouseCreateDTO> sortByName(){
        return warehouseCreateAssembler.toDto(warehouseRepository.findAllByOrderByNameAsc());
    }
    public List<WarehouseCreateDTO> pagination(Integer page){
        Pageable pages = PageRequest.of(page, 20);
        return  warehouseCreateAssembler.toDto(warehouseRepository.findAll(pages).getContent());
    }
    public List<WarehouseCreateDTO> findByName(String name){
        if(!warehouseRepository.existsByName(name)) throw new RuntimeException("There is no warehouse with such name");
        else return warehouseCreateAssembler.toDto(warehouseRepository.findByName(name));
    }
    private Boolean emptyGap(WarehouseDTO warehouseDTO){
        return warehouseDTO.getListOfMedicines() == null||
                warehouseDTO.getListOfUsers() == null ||
                warehouseDTO.getName() == null;
    }
    private Boolean emptyGap(WarehouseCreateDTO warehouseDTO){
        return warehouseDTO.getListOfMedicines() == null||
                warehouseDTO.getListOfUsers() == null ||
                warehouseDTO.getName() == null;
    }
}
