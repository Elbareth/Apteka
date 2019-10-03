package com.example.apteka.controller;

import com.example.apteka.dto.LoginDTO;
import com.example.apteka.dto.UserDTO;
import com.example.apteka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping
    public UserDTO get(@RequestBody LoginDTO loginDTO){
        httpSession.setAttribute("login",loginDTO.getLogin());
        UserDTO userDTO = userService.getByLogin(loginDTO.getLogin(),loginDTO.getPassword());
        httpSession.setAttribute("role",userDTO.getRole());
        return userDTO;
    }
}
