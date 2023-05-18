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
    public List<Teachers> getByCondition(TeacherPassParam teacherPassParam, Integer minAge, Integer maxAge){
        return teacherMapper.getByCondition (teacherPassParam.getName(),
                teacherPassParam.getGender(),
                minAge,
                maxAge,
                teacherPassParam.getInClass(),
                teacherPassParam.getTitle(),
                teacherPassParam.getPhone(),
                teacherPassParam.getEmail(),
                teacherPassParam.getAddr());
    }

    public void deleteById(Long id){
        teacherMapper.deleteById(id);
    }
    public void deleteByCondition(TeacherPassParam teacherPassParam, Integer minAge, Integer maxAge){
        teacherMapper.deleteByCondition(teacherPassParam.getName(),
                teacherPassParam.getGender(),
                minAge,
                maxAge,
                teacherPassParam.getInClass(),
                teacherPassParam.getTitle(),
                teacherPassParam.getPhone(),
                teacherPassParam.getEmail(),
                teacherPassParam.getAddr());
    }

    public void putById(Long id, TeacherPassParam teacherPassParam){
        Teachers teachers = new Teachers(
                id,
                teacherPassParam.getName(),
                teacherPassParam.getGender(),
                teacherPassParam.getAge(),
                teacherPassParam.getInClass(),
                teacherPassParam.getTitle(),
                teacherPassParam.getPhone(),
                teacherPassParam.getEmail(),
                teacherPassParam.getAddr() );
        teacherMapper.putById(teachers);
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
