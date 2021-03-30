package com.melo.notes.entity;

/**
 * @author Jun
 * @program Note
 * @description 用户实体类
 * @date 2021-3-27 9:38
 */
public class User {

    private Integer id;
    private String userName;
    private String password;


    public User() {
    }

    public User(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password) {
        this.userName=userName;
        this.password=password;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
