package com.example.backend_crud.mapper;

import com.example.backend_crud.entity.Students;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    List<Students> getAll();
    List<Students> getByCondition(@Param("name") String name,
                                  @Param("gender") String gender,
                                  @Param("inClass") Integer inClass,
                                  @Param("minAge") Integer minAge,
                                  @Param("maxAge") Integer maxAge,
                                  @Param("phone") String phone,
                                  @Param("addr") String addr);
    Students getById(@Param("id") Long id);


    void deleteById(@Param("id") Long id);
    void deleteByCondition(@Param("name") String name,
                           @Param("gender") String gender,
                           @Param("inClass") Integer inClass,
                           @Param("minAge") Integer minAge,
                           @Param("maxAge") Integer maxAge,
                           @Param("phone") String phone,
                           @Param("addr") String addr);


    void putById(@Param("id") Long id);


    void postStudent(@Param("name") String name,
                     @Param("gender") String gender,
                     @Param("inClass") Integer inClass,
                     @Param("age") Integer age,
                     @Param("phone") String phone,
                     @Param("addr") String addr);
}
