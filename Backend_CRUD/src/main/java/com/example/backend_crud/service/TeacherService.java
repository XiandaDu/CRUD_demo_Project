package com.example.backend_crud.service;

import com.example.backend_crud.entity.TeacherPassParam;
import com.example.backend_crud.entity.Teachers;
import com.example.backend_crud.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    public List<Teachers> getAll(){
        return teacherMapper.getAll();
    }
    public Teachers getById(Long id){
        return teacherMapper.getById(id);
    }
    public List<Teachers> getByCondition(TeacherPassParam teacherPassParam){
        return teacherMapper.getByCondition (teacherPassParam.getName(),
                teacherPassParam.getGender(),
                teacherPassParam.getMinAge(),
                teacherPassParam.getMaxAge(),
                teacherPassParam.getInClass(),
                teacherPassParam.getTitle(),
                teacherPassParam.getPhone(),
                teacherPassParam.getEmail(),
                teacherPassParam.getAddr());
    }

    public void deleteById(Long id){
        teacherMapper.deleteById(id);
    }
    public void deleteByCondition(TeacherPassParam teacherPassParam){
        teacherMapper.deleteByCondition(teacherPassParam.getName(),
                teacherPassParam.getGender(),
                teacherPassParam.getMinAge(),
                teacherPassParam.getMaxAge(),
                teacherPassParam.getInClass(),
                teacherPassParam.getTitle(),
                teacherPassParam.getPhone(),
                teacherPassParam.getEmail(),
                teacherPassParam.getAddr());
    }

    public void putById(Teachers teachers){
        Teachers teacher = new Teachers(
                teachers.getId(),
                teachers.getName(),
                teachers.getGender(),
                teachers.getAge(),
                teachers.getInClass(),
                teachers.getTitle(),
                teachers.getPhone(),
                teachers.getEmail(),
                teachers.getAddr());
        teacherMapper.putById(teacher);
    }

    public void postTeacher(Teachers teachers){
        teacherMapper.postTeacher(
                teachers.getName(),
                teachers.getGender(),
                teachers.getAge(),
                teachers.getInClass(),
                teachers.getTitle(),
                teachers.getPhone(),
                teachers.getEmail(),
                teachers.getAddr());
    }

}
