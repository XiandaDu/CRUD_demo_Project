package com.example.backend_crud.service;

import com.example.backend_crud.entity.Students;
import com.example.backend_crud.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Students> getAll(){
        return studentMapper.getAll();
    }
    public Students getById(Long id){
        return studentMapper.getById(id);
    }
    public List<Students> getByCondition(String name, String gender, Integer inClass, Integer minAge, Integer maxAge, String phone, String addr){
        return studentMapper.getByCondition(name, gender, inClass, minAge, maxAge, phone, addr);
    }

    public void deleteById(Long id){
        studentMapper.deleteById(id);
    }
    public void deleteByCondition(String name, String gender, Integer inClass, Integer minAge, Integer maxAge, String phone, String addr){
        studentMapper.deleteByCondition(name, gender, inClass, minAge, maxAge, phone, addr);
    }

    public void putById(Long id){
        studentMapper.putById(id);
    }


    public void postStudent( String name, String gender, Integer inClass, Integer age, String phone, String addr){
        studentMapper.postStudent(name, gender, inClass, age, phone, addr);
    }

}
