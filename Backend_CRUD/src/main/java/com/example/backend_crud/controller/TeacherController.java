package com.example.backend_crud.controller;

import com.example.backend_crud.entity.Teachers;
import com.example.backend_crud.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService TeacherService;

    @GetMapping("/getAll")
    public List<Teachers> getAll() {
        return TeacherService.getAll();
    }

    @GetMapping("/get/{id}")
    public Teachers getById(@PathVariable Long id) {
        return TeacherService.getById(id);
    }

    @GetMapping("/getByCondition")
    public List<Teachers> getByCondition(String name, String gender, Integer inClass, Integer minAge, Integer maxAge, String phone, String addr, String title, String email) {
        return TeacherService.getByCondition(name, gender, inClass, minAge, maxAge, phone, addr,title,email);
    }


    @PutMapping("/put")
    public void putById(@RequestBody Teachers Teachers) {
        TeacherService.putById(Teachers);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        TeacherService.deleteById(id);
    }
    @DeleteMapping("/deleteByCondition")
    public void deleteByCondition(String name, String gender, Integer inClass, Integer minAge, Integer maxAge, String phone, String addr, String title, String email) {
        TeacherService.deleteByCondition(name, gender, inClass, minAge, maxAge, phone, addr,title,email);
    }

    @PostMapping("/post")
    public void postTeacher(String name, String gender, Integer inClass, Integer age, String phone, String addr, String title, String email) {
        TeacherService.postTeacher(name, gender, inClass, age, phone, addr,title,email);
    }
}
