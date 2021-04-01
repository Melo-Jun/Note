package com.melo.notes.entity;

import com.melo.notes.entity.abs.BaseEntity;

/**
 * @author Jun
 * @program Note
 * @description 笔记分组实体类
 * @date 2021-3-29 21:34
 */
public class Group extends BaseEntity {

    private String groupName;
    private String authorId;
    private String locatedFolder;

    public Group() {
    }

    public Group(String groupName, String authorId, String locatedFolder) {
        this.groupName = groupName;
        this.authorId = authorId;
        this.locatedFolder = locatedFolder;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getLocatedFolder() {
        return locatedFolder;
    }

    public void setLocatedFolder(String locatedFolder) {
        this.locatedFolder = locatedFolder;
    }

}
