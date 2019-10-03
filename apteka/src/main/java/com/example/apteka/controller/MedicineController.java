package com.example.apteka.controller;

import com.example.apteka.dto.MedicineDTO;
import com.example.apteka.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("{id}") // all user can do this
    public MedicineDTO getById(@PathVariable("id") Integer id){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return medicineService.getById(id);
    }
    @GetMapping // all user can do this
    public List<MedicineDTO> getAll(){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return medicineService.getAll();
    }
    @PostMapping // only admin can do this
    public MedicineDTO create(@RequestBody MedicineDTO medicineDTO){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return medicineService.create(medicineDTO);
    }
    @DeleteMapping("{id}") // only admin ca do this
    public void delete(@PathVariable("id") Integer id){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else medicineService.delete(id);
    }
    @PatchMapping("{id}") // only admin can do this
    public MedicineDTO update(@PathVariable("id") Integer id, @RequestBody MedicineDTO medicineDTO){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else if(httpSession.getAttribute("role").equals("User")) throw new RuntimeException("Sorry. You haven't got permission to this activity");
        else return medicineService.update(id, medicineDTO);
    }
    @GetMapping("/sort") // all user can do this
    public List<MedicineDTO> sort(){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return medicineService.sortByName();
    }
    @GetMapping("/page/{page}") // all user can do this
    public List<MedicineDTO> pagination(@PathVariable("page") Integer page){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return medicineService.pagination(page);
    }
    @GetMapping("/name/{name}")
    public List<MedicineDTO> findByName(@PathVariable("name") String name){
        if(httpSession.getAttribute("login") == null ) throw new RuntimeException("You are not logged");
        else return medicineService.findByName(name);
    }
}
