package com.example.backend_crud;

import com.example.backend_crud.controller.StudentController;
import com.example.backend_crud.entity.Students;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.List;

@SpringBootTest
class BackendCrudApplicationTests {
    @Autowired
    private StudentController studentController;

    @Test
    void postStudent(){
        //name, gender, inClass, age, phone, addr
        studentController.postStudent("Bill", "Female", 202201, 12, "12244432333", "xx rd. Hamilton, ON");
        System.out.println("XXXXXXXXXXXXXXXXXXXXX这是一条分割线");
    }

}
