package com.example.backend_crud.service;

import com.example.backend_crud.entity.StudentPassParam;
import com.example.backend_crud.entity.Students;
import com.example.backend_crud.DAO.StudentMapper;
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
    public List<Students> getByCondition(StudentPassParam studentPassParam){
        return studentMapper.getByCondition(studentPassParam.getName(),
                                            studentPassParam.getGender(),
                                            studentPassParam.getInClass(),
                                            studentPassParam.getMinAge(),
                                            studentPassParam.getMaxAge(),
                                            studentPassParam.getPhone(),
                                            studentPassParam.getAddr() );
    }

    public void deleteById(Long id){
        studentMapper.deleteById(id);
    }
    public void deleteByCondition(StudentPassParam studentPassParam){
        studentMapper.deleteByCondition(studentPassParam.getName(),
                                        studentPassParam.getGender(),
                                        studentPassParam.getInClass(),
                                        studentPassParam.getMinAge(),
                                        studentPassParam.getMaxAge(),
                                        studentPassParam.getPhone(),
                                        studentPassParam.getAddr() );
    }

    public void putById(Students students){
        Students student = new Students(students.getId(),
                                        students.getName(),
                                        students.getGender(),
                                        students.getInClass(),
                                        students.getAge(),
                                        students.getPhone(),
                                        students.getAddr() );
         studentMapper.putById(student);
    }


    public void postStudent(Students students){
        studentMapper.postStudent(  students.getName(),
                                    students.getGender(),
                                    students.getInClass(),
                                    students.getAge(),
                                    students.getPhone(),
                                    students.getAddr());
    }

}
