package com.melo.notes.entity;

/**
 * @author Jun
 * @program Note
 * @description 知识库实体类
 * @date 2021-3-29 21:40
 */
public class Folder {

    private Integer id;
    private String folderName;
    private String author;
    private String access;

    public Folder() {
    }

    public Folder(Integer id, String folderName, String author, String access) {
        this.id = id;
        this.folderName = folderName;
        this.author = author;
        this.access = access;
    }

    public Integer getId() {
        return id;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getAuthor() {
        return author;
    }

    public String getAccess() {
        return access;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", folderName='" + folderName + '\'' +
                ", author='" + author + '\'' +
                ", access='" + access + '\'' +
                '}';
    }
}
