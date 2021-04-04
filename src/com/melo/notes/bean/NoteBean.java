package com.melo.notes.bean;

/**
 * @author Jun
 * @program Note
 * @description 将信息封装成笔记对象(便于sql语句的填充)
 * @date 2021-4-4 12:27
 */
public class NoteBean {

    private String access;
    private String title;

    public NoteBean(String access, String title) {
        this.access = access;
        this.title = title;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
