package com.melo.notes.entity;

import com.melo.notes.entity.abs.BaseEntity;

/**
 * @author Jun
 * @program Note
 * @description 用户实体类
 * @date 2021-3-27 9:38
 */
public class Admin extends BaseEntity {

    private String adminName;
    private String password;


    public Admin() {
    }

    public Admin(String userName, String password) {
        this.adminName = userName;
        this.password = password;
    }


    public String getAdminName() {
        return adminName;
    }

    public String getPassword() {
        return password;
    }


    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
