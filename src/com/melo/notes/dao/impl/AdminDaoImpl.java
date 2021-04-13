package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.AdminDao;
import com.melo.notes.entity.Admin;

import java.util.LinkedList;

import static com.melo.notes.util.JdbcUtils.getTableName;
import static com.melo.notes.util.Md5Utils.getDigest;

/**
 * @author Jun
 * @program Note
 * @description 管理员数据库操作实现类
 * @date 2021-4-10 8:19
 */
public class AdminDaoImpl  extends BaseDaoImpl implements AdminDao {

    /**
     * 验证是否为管理员
     *
     * @param admin 管理员对象
     * @return boolean 是否为管理员
     */
    @Override
    public boolean isAdmin(Admin admin) {
        String sql = "select password from " + getTableName(admin) + " where admin_name=? ";
        /*
        构造临时对象来执行sql(根据管理员名称)
         */
        Admin tempAdmin = new Admin();
        tempAdmin.setAdminName(admin.getAdminName());
        LinkedList passwords = queryList(sql, tempAdmin);
        if (passwords.isEmpty()) {
            return false;
        }
        return passwords.getFirst().equals(getDigest(admin.getPassword()));
    }

}

