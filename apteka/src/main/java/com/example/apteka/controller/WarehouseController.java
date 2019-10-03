package com.example.apteka.controller;

import com.example.apteka.dto.WarehouseCreateDTO;
import com.example.apteka.dto.WarehouseDTO;
import com.example.apteka.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("{id}")// all user can do this
    public WarehouseDTO getById(@PathVariable("id") Integer id){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return warehouseService.getById(id);
    }
    @GetMapping // all user can do this
    public List<WarehouseCreateDTO> getAll(){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return warehouseService.getAll();
    }
    @PostMapping // only admin can do this
    public WarehouseCreateDTO create(@RequestBody WarehouseCreateDTO warehouseDTO){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return warehouseService.create(warehouseDTO);
    }
    @PatchMapping("{id}") // only admin can do this
    public WarehouseDTO update(@PathVariable("id") Integer id, @RequestBody WarehouseDTO warehouseDTO){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return warehouseService.update(id, warehouseDTO);

    }
    @DeleteMapping("{id}") // only admin can do this
    public void delete(@PathVariable("id") Integer id){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else warehouseService.delete(id);

    }
    @GetMapping("/sort") // all user can do this
    public List<WarehouseCreateDTO> sort(){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return warehouseService.sortByName();

    }
    @GetMapping("/page/{page}") // all user can do this
    public List<WarehouseCreateDTO> pagination(@PathVariable("page") Integer page){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return warehouseService.pagination(page);
    }
    @GetMapping("/name/{name}")
    public List<WarehouseCreateDTO> findByName(@PathVariable("name") String name){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return warehouseService.findByName(name);
    }
}
