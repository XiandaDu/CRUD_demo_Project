package com.example.backend_crud.controller;

import com.example.backend_crud.entity.StudentPassParam;
import com.example.backend_crud.entity.Students;
import com.example.backend_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
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
    public List<Students> getByCondition( StudentPassParam studentPassParam) {
        return studentService.getByCondition(studentPassParam);
    }

    @PutMapping("/put")
    public void putById(@RequestBody Students students) {
        studentService.putById(students);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @DeleteMapping("/deleteByCondition")
    public void deleteByCondition(StudentPassParam studentPassParam) {
        studentService.deleteByCondition(studentPassParam);
    }

    @PostMapping("/post")
    public void postStudent(@RequestBody Students students) {
        studentService.postStudent(students);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
