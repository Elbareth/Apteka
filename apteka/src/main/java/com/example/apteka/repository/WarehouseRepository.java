package com.example.apteka.repository;

import com.example.apteka.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("WarehouseRepository")
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Integer> {
    List<WarehouseEntity> findAllByOrderByNameAsc();
    List<WarehouseEntity> findByName(String name);
    Boolean existsByName(String name);
}
