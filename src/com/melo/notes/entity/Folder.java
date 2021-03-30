package com.melo.notes.entity;

import com.melo.notes.entity.abs.BaseEntity;

/**
 * @author Jun
 * @program Note
 * @description 知识库实体类
 * @date 2021-3-29 21:40
 */
public class Folder extends BaseEntity {

    private String folderName;
    private String authorName;
    private String access;

    public Folder() {
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

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAccess(String access) {
        this.access = access;
    }

}
