package com.example.backend_crud.controller;

import com.example.backend_crud.entity.Log;
import com.example.backend_crud.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
@CrossOrigin
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/getAll")
    public List<Log> getAll() { return logService.getAll(); }

    @PostMapping("/post")
    public void postLog(@RequestBody Log logs){ logService.postLog(logs); }
}
