package com.melo.notes.dao.impl;

import com.melo.notes.entity.Admin;
import com.melo.notes.entity.User;
import com.melo.notes.util.JdbcUtils;

import javax.xml.transform.sax.SAXSource;
import java.util.LinkedList;

import static com.melo.notes.util.JdbcUtils.getTableName;
import static com.melo.notes.util.Md5Utils.getDigest;

public class AdminDaoImpl extends BaseDaoImpl{

    public boolean isAdmin(Admin admin){
        String sql="select password from "+ getTableName(admin)+" where admin_name=? ";
        Admin tempAdmin = new Admin();
        tempAdmin.setAdminName(admin.getAdminName());
        LinkedList<Object> objects =  queryList(sql, tempAdmin);
        if(objects.isEmpty()){
            return false;
        }
        if(objects.getFirst().equals(getDigest(admin.getPassword()))){
            return true;
        }
        return false;
    }
}

