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
    private String authorName;
    private String access;

    public Folder() {
    }

    public Folder(Integer id, String folderName, String authorName, String access) {
        this.id = id;
        this.folderName = folderName;
        this.authorName = authorName;
        this.access = access;
    }


    public String getFolderName() {
        return folderName;
    }

    public String getAuthorName() {
        return authorName;
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

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", folderName='" + folderName + '\'' +
                ", author='" + authorName + '\'' +
                ", access='" + access + '\'' +
                '}';
    }
}
