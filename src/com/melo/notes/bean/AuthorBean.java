package com.melo.notes.bean;

public class AuthorBean {

    private String authorId;

    public AuthorBean(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
