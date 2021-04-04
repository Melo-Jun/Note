package com.melo.notes.bean;

/**
 * @author Jun
 * @program Note
 * @description 将信息封装成用户对象(便于sql语句的填充)
 * @date 2021-4-4 12:24
 */
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
