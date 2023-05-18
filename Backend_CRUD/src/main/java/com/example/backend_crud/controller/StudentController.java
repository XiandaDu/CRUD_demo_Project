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
    public List<Students> getByCondition(StudentPassParam studentPassParam, Integer minAge, Integer maxAge) {
        return studentService.getByCondition(studentPassParam, minAge, maxAge);
    }


    @PutMapping("/put")
    public void putById(Long id ,StudentPassParam studentPassParam) {
        studentService.putById(id, studentPassParam);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @DeleteMapping("/deleteByCondition")
    public void deleteByCondition(StudentPassParam studentPassParam, Integer minAge, Integer maxAge) {
        studentService.deleteByCondition(studentPassParam, minAge, maxAge);
    }

    @PostMapping("/post")
    public void postStudent(Students students) {
        studentService.postStudent(students);
    }
}
