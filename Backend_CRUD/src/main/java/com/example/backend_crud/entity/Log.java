package com.example.backend_crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private String objectType;
    private Long modiStudId;
    private Long modiTeacId;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Long getModiStudId() {
        return modiStudId;
    }

    public void setModiStudId(Long modiStudId) {
        this.modiStudId = modiStudId;
    }

    public Long getModiTeacId() {
        return modiTeacId;
    }

    public void setModiTeacId(Long modiTeacId) {
        this.modiTeacId = modiTeacId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
