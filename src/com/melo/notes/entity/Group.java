package com.melo.notes.entity;

/**
 * @author Jun
 * @program Note
 * @description 笔记分组实体类
 * @date 2021-3-29 21:34
 */
public class Group {

    private Integer id;
    private String groupName;
    private String authorName;
    private String locatedFolder;

    public Group() {
    }

    public Group(Integer id, String groupName, String author, String locatedFolder) {
        this.id = id;
        this.groupName = groupName;
        this.authorName = author;
        this.locatedFolder = locatedFolder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", author='" + authorName + '\'' +
                ", locatedFolder='" + locatedFolder + '\'' +
                '}';
    }
}
