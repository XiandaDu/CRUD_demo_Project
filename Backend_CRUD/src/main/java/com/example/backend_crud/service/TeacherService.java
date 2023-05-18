package com.example.backend_crud.service;

import com.example.backend_crud.entity.Teachers;
import com.example.backend_crud.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherService {
    @Autowired
    private TeacherMapper TeacherMapper;

    public List<Teachers> getAll(){
        return TeacherMapper.getAll();
    }
    public Teachers getById(Long id){
        return TeacherMapper.getById(id);
    }
    public List<Teachers> getByCondition(String name, String gender, Integer inClass, Integer minAge, Integer maxAge, String phone, String addr, String title, String email){
        return TeacherMapper.getByCondition(name, gender, inClass, minAge, maxAge, phone, addr, title, email);
    }

    public void deleteById(Long id){
        TeacherMapper.deleteById(id);
    }
    public void deleteByCondition(String name, String gender, Integer inClass, Integer minAge, Integer maxAge, String phone, String addr, String title, String email){
        TeacherMapper.deleteByCondition(name, gender, inClass, minAge, maxAge, phone, addr, title, email);
    }

    public void putById(Teachers Teachers){
        TeacherMapper.putById(Teachers);
    }


    public void postTeacher(String name, String gender, Integer inClass, Integer age, String phone, String addr, String title, String email){
        TeacherMapper.postTeacher(name, gender, inClass, age, phone, addr, title, email);
    }
}
