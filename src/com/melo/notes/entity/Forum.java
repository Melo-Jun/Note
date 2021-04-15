package com.melo.notes.entity;

import com.melo.notes.entity.abs.BaseEntity;

/**
 * @author Jun
 * @program Note
 * @description 论坛实体类
 * @date 2021-4-15 15:51
 */
public class Forum extends BaseEntity {
    private String title;
    private String text;
    private String userId;

    public Forum() {
    }

    public Forum(String title, String text, String userId) {
        this.title = title;
        this.text = text;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
