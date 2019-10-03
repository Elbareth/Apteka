package com.example.apteka.service;

import com.example.apteka.assembler.MedicineAssembler;
import com.example.apteka.dto.MedicineDTO;
import com.example.apteka.entity.MedicineEntity;
import com.example.apteka.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MedicineAssembler medicineAssembler;

    public MedicineDTO getById(Integer id){
        if(!medicineRepository.existsById(id)){
            throw new EntityNotFoundException("There is no entity with this id");
        }
        else return medicineAssembler.toDto(medicineRepository.getOne(id));
    }

    public List<MedicineDTO> getAll(){
        return medicineAssembler.toDto(medicineRepository.findAll());
    }
    public MedicineDTO create(MedicineDTO medicineDTO){
        if(emptyGap(medicineDTO)) throw new RuntimeException("Please fill all gap");
        else return medicineAssembler.toDto(medicineRepository.save(medicineAssembler.toEntity(medicineDTO)));
    }
    @Transactional
    public MedicineDTO update(Integer id, MedicineDTO medicineDTO){
        if(!medicineRepository.existsById(id)){
            throw new EntityNotFoundException("There is no entity with this id");
        }
        else if(emptyGap(medicineDTO)) throw new RuntimeException("Please fill all gap");
        else {
            MedicineEntity medicineEntity = medicineRepository.getOne(id);
            medicineAssembler.updateEntity(medicineEntity, medicineDTO);
            return medicineDTO;
        }
    }
    public void delete(Integer id){
        if(!medicineRepository.existsById(id)){
            throw new EntityNotFoundException("There is no entity with this id");
        }
        else medicineRepository.deleteById(id);
    }
    public List<MedicineDTO> sortByName(){
        return medicineAssembler.toDto(medicineRepository.findAllByOrderByNameAsc());
    }
    public List<MedicineDTO> pagination(Integer page){
        Pageable pages = PageRequest.of(page, 20);
        return  medicineAssembler.toDto(medicineRepository.findAll(pages).getContent());
    }
    public List<MedicineDTO> findByName(String name){
        if(!medicineRepository.existsByName(name)) throw new RuntimeException("There is no medicine with such name");
        else return medicineAssembler.toDto(medicineRepository.findByName(name));
    }
    private Boolean emptyGap(MedicineDTO medicineDTO){
        return medicineDTO.getPrice() == null ||
                medicineDTO.getName() == null ||
                medicineDTO.getDescription() == null ||
                medicineDTO.getDateOfUse() == null;
    }
}
