package com.melo.notes.entity.abs;

import java.util.Date;

/**
 * @author jun
 * @program Note
 * @description 所有数据库记录的父类
 * @date 2021-3-30 14:58
 */
public abstract class BaseEntity {

    private String id;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
