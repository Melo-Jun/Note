package com.melo.notes.entity.abs;

/**
 * @author jun
 * @program Note
 * @description 所有数据库记录的父类
 * @date 2021-3-30 14:58
 */
public abstract class BaseEntity {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
