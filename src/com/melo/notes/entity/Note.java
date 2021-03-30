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
    private Date creatTime;
    private Date updateTime;
    private Integer likeCount;
    private String locatedGroup;

    public Note() {
    }

    public Note(Integer id, String title, String authorName, String text, String access, Date creatTime, Date updateTime, Integer likeCount, String locatedGroup) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.text = text;
        this.access = access;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
        this.likeCount = likeCount;
        this.locatedGroup = locatedGroup;
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

    public void setAuthorName(String author) {
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
