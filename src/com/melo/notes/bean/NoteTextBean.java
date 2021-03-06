package com.melo.notes.bean;

/**
 * @author Jun
 * @program Note
 * @description 将信息封装成用户对象(便于sql语句的填充)
 * @date 2021-4-4 17:50
 */
public class NoteTextBean {

    private String title;

    public NoteTextBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
