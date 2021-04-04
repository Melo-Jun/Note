package com.melo.notes.bean;

/**
 * @author Jun
 * @program Note
 * @description 将信息封装成用户对象(便于sql语句的填充)
 * @date 2021-4-4 17:50
 */
public class NoteTextBean {

    private String title;
    /*private int offset;
    private int size;*/

    public NoteTextBean(String title) {
        this.title = title;
    }

   /* public NoteTextBean(String title, int offset, int size) {
        this.title = title;
        this.offset = offset;
        this.size = size;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   /* public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }*/
}
