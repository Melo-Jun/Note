package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.UserDao;
import com.melo.notes.entity.User;
import com.melo.notes.exception.DaoException;

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

    /**
     * 验证是否已存在该用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean isExist(User user) {
        String sql="select user_name from user where user_name=? ";
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
        String sql="select password from "+getTableName(user) +" where user_name=? ";
        User obj = new User();
        obj.setUserName(user.getUserName());
        LinkedList<Object> objects = queryList(sql, obj);
        if(objects.isEmpty()){
            return false;
        }
        if(objects.getFirst().equals(getDigest(user.getPassword()))){
            return true;
        }return false;
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
     * 设置Id
     * @param user
     * @return boolean 是否增加成功
     */
    @Override
    public boolean setId(User user) {
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select id from user where user_name=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            rs=ps.executeQuery();
            rs.next();
            String id = rs.getString("id");
            user.setId(id);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(ps,rs);
        }
        return false;
    }


}
