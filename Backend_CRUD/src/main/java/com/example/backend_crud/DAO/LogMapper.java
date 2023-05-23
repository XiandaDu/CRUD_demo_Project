package com.example.backend_crud.DAO;

import com.example.backend_crud.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {
    List<Log> getAll();

    void postLog (  @Param("action") String action,
                    @Param("objectType") String objectType,
                    @Param("modiStudId") Long modiStudId,
                    @Param("modiTeacId") Long modiTeacId );
}
