package com.example.apteka.repository;

import com.example.apteka.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MedicineRepository")
public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {
    List<MedicineEntity> findAllByOrderByNameAsc();
    List<MedicineEntity> findByName(String name);
    Boolean existsByName(String name);
}
