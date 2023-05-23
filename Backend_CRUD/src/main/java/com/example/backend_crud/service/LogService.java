package com.example.backend_crud.service;

import com.example.backend_crud.entity.Log;
import com.example.backend_crud.DAO.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    public List<Log> getAll(){
        return logMapper.getAll();
    };

    public void postLog(Log logs){
        logMapper.postLog(logs.getAction(), logs.getObjectType(), logs.getModiStudId(), logs.getModiTeacId());
    };

}
