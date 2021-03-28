package com.melo.notes.entity;

import java.util.Date;

/**
 * @author Jun
 * @program Note
 * @description 笔记实体类
 * @date 2021-3-27 21:07
 */
public class Note {

    private Integer id;
    private String title;
    private String authorName;
    private String text;
    private String access;
    private Integer likeCount;
    private String locatedGroup;

    public Note() {
    }

    public Note(Integer id, String title, String authorName, String text, String access, Integer likeCount, String locatedGroup) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.text = text;
        this.access = access;
        this.likeCount = likeCount;
        this.locatedGroup = locatedGroup;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.authorName = author;
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

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + authorName + '\'' +
                ", text='" + text + '\'' +
                ", access='" + access + '\'' +
                ", likeCount=" + likeCount +
                ", locatedGroup='" + locatedGroup + '\'' +
                '}';
    }
}
