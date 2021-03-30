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
    private String authorName;
    private String locatedFolder;

    public Group() {
    }

    public Group(String groupName, String authorName, String locatedFolder) {
        this.groupName = groupName;
        this.authorName = authorName;
        this.locatedFolder = locatedFolder;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getLocatedFolder() {
        return locatedFolder;
    }

    public void setLocatedFolder(String locatedFolder) {
        this.locatedFolder = locatedFolder;
    }

}
