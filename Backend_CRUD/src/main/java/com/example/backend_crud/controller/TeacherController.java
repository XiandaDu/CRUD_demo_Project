package com.example.backend_crud.controller;

import com.example.backend_crud.entity.Teachers;
import com.example.backend_crud.entity.TeacherPassParam;
import com.example.backend_crud.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
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
    public List<Teachers> getByCondition(TeacherPassParam teacherPassParam, Integer minAge, Integer maxAge) {
        return teacherService.getByCondition(teacherPassParam, minAge, maxAge);
    }


    @PutMapping("/put")
    public void putById(Long id ,TeacherPassParam teacherPassParam) {
        teacherService.putById(id, teacherPassParam);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
    }

    @DeleteMapping("/deleteByCondition")
    public void deleteByCondition(TeacherPassParam teacherPassParam, Integer minAge, Integer maxAge) {
        teacherService.deleteByCondition(teacherPassParam, minAge, maxAge);
    }

    @PostMapping("/post")
    public void postTeacher(Teachers teachers) {
        teacherService.postTeacher(teachers);
    }
}
