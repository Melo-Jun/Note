package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.UserDao;
import com.melo.notes.entity.User;
import com.melo.notes.exception.DaoException;
import com.melo.notes.view.LoginView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static com.melo.notes.util.JdbcUtils.*;
import static com.melo.notes.util.Md5Utils.getDigest;

/**
 * @author Jun
 * @program Note
 * @description 用户类数据库操作实现类
 * @date 2021-3-28 20:45
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private String TABLE_NAME="user";

    /**
     * 验证是否已存在该用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean isExist(User user) {
        String sql="select user_name from "+ TABLE_NAME +" where user_name=? ";
        User obj = new User();
        obj.setUserName(user.getUserName());
        LinkedList<Object> objects = queryList(sql, obj);
        if(objects.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * 验证密码是否正确
     * @param user
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     * @return
     */
    @Override
    public boolean judgePass(User user){
        String sql="select password from "+TABLE_NAME+" where user_name=? ";
        User obj = new User();
        obj.setUserName(user.getUserName());
        LinkedList<Object> objects = queryList(sql, obj);
        if(objects.isEmpty()){
            return false;
        }
        if(objects.getFirst().equals(getDigest(user.getPassword()))){
            return true;
        }
        return false;
    }

    /**
     * 增加用户
     * @param user
     * @return boolean 是否增加成功
     */
    @Override
    public boolean add(User user) {
        /**
         * 发现用户名为空也会被增加进去才修改
         */
        if(user==null||user.getUserName().isEmpty()){
            return false;
        }
        return super.insert(user) == 1;
    }

    /**
     * 为登录进来的用户设置Id以便后续查询
     * @param user
     * @return boolean 是否增加成功
     */
    @Override
    public void setId(User user) {
            String sql="select id from "+TABLE_NAME+ " where user_name=?";
            LinkedList id = queryList(sql, user);
            if(!id.isEmpty()) {
                System.out.println("用户的id是"+id.getFirst().toString());
                LoginView.USER.setId(id.getFirst().toString());
            }
    }
}
