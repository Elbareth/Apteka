package com.example.apteka.repository;

import com.example.apteka.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findAllByOrderByNameAsc();
    UserEntity findByLogin(String login);
    List<UserEntity> findBySurname(String surname);
    Boolean existsBySurname(String surname);
    Boolean existsByLoginAndPassword(String login, String password);
    Boolean existsByLogin(String login);
}
