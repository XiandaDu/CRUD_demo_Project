package com.example.backend_crud.controller;

import com.example.backend_crud.entity.Students;
import com.example.backend_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public List<Students> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/get/{id}")
    public Students getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @GetMapping("/getByCondition")
    public List<Students> getByCondition(@RequestParam String name, String gender, Integer inClass, Integer minAge, Integer maxAge, String phone, String addr) {
        return studentService.getByCondition(name, gender, inClass, minAge, maxAge, phone, addr);
    }


    @PutMapping("/put")
    public void putById(Long id ,String name, String gender, Integer inClass, Integer age, String phone, String addr) {
        studentService.putById(id, name, gender, inClass, age, phone, addr);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @DeleteMapping("/deleteByCondition")
    public void deleteByCondition(String name, String gender, Integer inClass, Integer minAge, Integer maxAge, String phone, String addr) {
        studentService.deleteByCondition(name, gender, inClass, minAge, maxAge, phone, addr);
    }

    @PostMapping("/post")
    public void postStudent(String name, String gender, Integer inClass, Integer age, String phone, String addr) {
        studentService.postStudent(name, gender, inClass, age, phone, addr);
    }
}
