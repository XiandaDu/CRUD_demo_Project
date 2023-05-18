package com.example.backend_crud.mapper;

import com.example.backend_crud.entity.Teachers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {
    List<Teachers> getAll();
    List<Teachers> getByCondition(@Param("name") String name,
                                  @Param("gender") String gender,
                                  @Param("minAge") Integer minAge,
                                  @Param("maxAge") Integer maxAge,
                                  @Param("inClass") Integer inClass,
                                  @Param("title") String title,
                                  @Param("phone") String phone,
                                  @Param("email") String email,
                                  @Param("addr") String addr);
    Teachers getById(@Param("id") Long id);

    void deleteById(@Param("id") Long id);
    void deleteByCondition(@Param("name") String name,
                           @Param("gender") String gender,
                           @Param("minAge") Integer minAge,
                           @Param("maxAge") Integer maxAge,
                           @Param("inClass") Integer inClass,
                           @Param("title") String title,
                           @Param("phone") String phone,
                           @Param("email") String email,
                           @Param("addr") String addr);

    void putById(Teachers teachers);


    void postTeacher(@Param("name") String name,
                     @Param("gender") String gender,
                     @Param("age") Integer age,
                     @Param("inClass") Integer inClass,
                     @Param("title") String title,
                     @Param("phone") String phone,
                     @Param("email") String email,
                     @Param("addr") String addr);
}
