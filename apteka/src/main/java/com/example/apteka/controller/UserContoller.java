package com.example.apteka.controller;

import com.example.apteka.dto.UserDTO;
import com.example.apteka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserContoller {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;

    @DeleteMapping ("{id}")// only for admin
    public void delete(@PathVariable("id") Integer id){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else userService.delete(id);
    }

    @GetMapping("{id}") // only for admin
    public UserDTO getById(@PathVariable("id") Integer id){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return userService.getById(id);
    }

    @GetMapping // only for admin
    public List<UserDTO> getAll(){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return userService.getAll();
    }

    @PatchMapping("{id}") // for all user
    public UserDTO update(@RequestBody  UserDTO userDTO, @PathVariable("id") Integer id){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(!httpSession.getAttribute("login").equals(userDTO.getLogin())) throw new RuntimeException(("Sorry. You can't update another user"));
        else return userService.update(id,userDTO);
    }

    @GetMapping("/sort") // only for admin
    public List<UserDTO> sort(){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return userService.sortByName();
    }

    @GetMapping("/page/{page}") // only for admin
    public List<UserDTO> pagination(@PathVariable("page") Integer page){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return userService.pagination(page);
    }
    @GetMapping("/login/{login}") // only admin ca do this
    public UserDTO findByLogin(@PathVariable("login") String login){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return userService.findByLogin(login);
    }
    @GetMapping("/surname/{surname}")
    public List<UserDTO> findBySurname(@PathVariable("surname") String surname){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return userService.findBySurname(surname);
    }
}
