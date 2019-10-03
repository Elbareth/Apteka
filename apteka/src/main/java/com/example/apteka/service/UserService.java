package com.example.apteka.service;

import com.example.apteka.assembler.UserAssembler;
import com.example.apteka.dto.UserDTO;
import com.example.apteka.entity.UserEntity;
import com.example.apteka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAssembler userAssembler;
    @Autowired
    private PasswordService passwordService;

    public UserDTO getById(Integer id){
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException("There is no entity with this id");
        }
        return userAssembler.toDto(userRepository.getOne(id));
    }

    public UserDTO getByLogin(String login, String password){
        if(login == null || password == null) throw new RuntimeException("Gap can't be empty");
        String hashPassword = passwordService.codePassword(password);
        if(!userRepository.existsByLoginAndPassword(login, hashPassword)){
            throw new EntityNotFoundException("Login ar password You enter is not valid");
        }
        else return userAssembler.toDto(userRepository.findByLogin(login));
    }

    public List<UserDTO> getAll(){
        return userAssembler.toDto(userRepository.findAll());
    }
    public UserDTO create(UserDTO userDTO){
        userDTO.setPassword(passwordService.codePassword(userDTO.getPassword()));
        if(emptyGap(userDTO)) throw new RuntimeException("Please fill all gaps");
        else if(userRepository.existsByLogin(userDTO.getLogin())) throw new RuntimeException("Login You choose in already in use");
        else return userAssembler.toDto(userRepository.save(userAssembler.toEntity(userDTO)));
    }
    @Transactional
    public UserDTO update(Integer id, UserDTO userDTO){
        if(emptyGap(userDTO)) throw new RuntimeException("Please fill all gaps");
        else if(!userRepository.existsById(id)){
            throw new EntityNotFoundException("There is no entity with this id");
        }
        else {
            userDTO.setPassword(passwordService.codePassword(userDTO.getPassword()));
            UserEntity userEntity = userRepository.getOne(id);
            if(!userEntity.getLogin().equals(userDTO.getLogin())) throw new RuntimeException("You can't change your login!");
            userAssembler.updateEntity(userEntity, userDTO);
            return userDTO;
        }
    }
    public void delete(Integer id){
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException("There is no entity with this id");
        }
        else userRepository.deleteById(id);
    }
    public List<UserDTO> sortByName(){
        return userAssembler.toDto(userRepository.findAllByOrderByNameAsc());
    }
    public List<UserDTO> pagination(Integer page){
        Pageable pages = PageRequest.of(page, 20);
        return  userAssembler.toDto(userRepository.findAll(pages).getContent());
    }
    public UserDTO findByLogin(String login){
        if(!userRepository.existsByLogin(login)) throw new RuntimeException("There is no user with such login");
        else return userAssembler.toDto(userRepository.findByLogin(login));
    }
    public List<UserDTO> findBySurname(String surname){
        if(!userRepository.existsBySurname(surname)) throw new RuntimeException("There is no user with such surname");
        else return userAssembler.toDto(userRepository.findBySurname(surname));
    }
    private Boolean emptyGap(UserDTO userDTO){
        return userDTO.getLogin() == null||
                userDTO.getName() == null||
                userDTO.getPassword() == null||
                userDTO.getRole() == null||
                userDTO.getSurname() == null;
    }
}
