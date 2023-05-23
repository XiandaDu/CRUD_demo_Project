package com.example.backend_crud.controller;

import com.example.backend_crud.entity.Teachers;
import com.example.backend_crud.entity.TeacherPassParam;
import com.example.backend_crud.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getAll")
    public List<Teachers> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/get/{id}")
    public Teachers getById(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @GetMapping("/getByCondition")
    public List<Teachers> getByCondition(TeacherPassParam teacherPassParam) {
        return teacherService.getByCondition(teacherPassParam);
    }

    @PutMapping("/put")
    public void putById(@RequestBody Teachers teachers) {
        teacherService.putById(teachers);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
    }

    @DeleteMapping("/deleteByCondition")
    public void deleteByCondition(TeacherPassParam teacherPassParam) {
        teacherService.deleteByCondition(teacherPassParam);
    }

    @PostMapping("/post")
    public void postTeacher(@RequestBody Teachers teachers) {
        teacherService.postTeacher(teachers);
    }
}
