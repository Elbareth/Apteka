package com.example.apteka.controller;

import com.example.apteka.dto.UserDTO;
import com.example.apteka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO){
        return userService.create(userDTO);
    }
}
