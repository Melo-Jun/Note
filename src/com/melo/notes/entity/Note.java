package com.melo.notes.entity;

import com.melo.notes.entity.abs.BaseEntity;

/**
 * @author Jun
 * @program Note
 * @description 笔记实体类
 * @date 2021-3-27 21:07
 */
public class Note extends BaseEntity {

    private String title;
    private String authorId;
    private String text;
    private String access;
    private Integer likeCount;
    private String locatedGroup;

    public Note() {
    }

    public Note(String title, String authorId, String text, String access, Integer likeCount, String locatedGroup) {
        this.title = title;
        this.authorId = authorId;
        this.text = text;
        this.access = access;
        this.likeCount = likeCount;
        this.locatedGroup = locatedGroup;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getText() {
        return text;
    }

    public String getAccess() {
        return access;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public String getLocatedGroup() {
        return locatedGroup;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorId(String author) {
        this.authorId = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public void setLocatedGroup(String locatedGroup) {
        this.locatedGroup = locatedGroup;
    }

}
