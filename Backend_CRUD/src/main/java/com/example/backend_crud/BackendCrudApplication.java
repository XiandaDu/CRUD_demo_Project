package com.example.backend_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class BackendCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendCrudApplication.class, args);
    }

}
