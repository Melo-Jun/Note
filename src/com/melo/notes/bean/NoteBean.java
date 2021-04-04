package com.melo.notes.bean;

/**
 * @author Jun
 * @program Note
 * @description 将信息封装成笔记对象
 * @date 2021-3-27 16:55
 */
public class NoteBean {

    private String title;

    public NoteBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
