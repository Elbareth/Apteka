package com.example.apteka.assembler;

import com.example.apteka.dto.UserDTO;
import com.example.apteka.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAssembler {

    public UserDTO toDto(UserEntity userEntity){
        return new UserDTO(
                userEntity.getLogin(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getPassword(),
                userEntity.getRole()
        );
    }

    public List<UserDTO> toDto(List<UserEntity> userEntities){
        List<UserDTO> listOfUserDto = new ArrayList<>();
        userEntities.forEach(param ->{
            listOfUserDto.add(toDto(param));
        });
        return listOfUserDto;
    }

    public UserEntity toEntity(UserDTO userDTO){
        return new UserEntity(
                userDTO.getLogin(),
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getPassword(),
                userDTO.getRole()
        );
    }

    public List<UserEntity> toEntity(List<UserDTO> userDTOS){
        List<UserEntity> listOfUserEntity = new ArrayList<>();
        userDTOS.forEach(param ->{
            listOfUserEntity.add(toEntity(param));
        });
        return listOfUserEntity;
    }

    public void updateEntity(UserEntity userEntity, UserDTO userDTO){
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setName(userDTO.getName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setSurname(userDTO.getSurname());
        userEntity.setRole(userDTO.getRole());
    }
}
