package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.UserDao;
import com.melo.notes.entity.User;
import com.melo.notes.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.melo.notes.util.JdbcUtils.*;

/**
 * @author Jun
 * @program Note
 * @description 用户类数据库操作实现类
 * @date 2021-3-28 20:45
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    /**
     * 增加用户
     * @param user
     * @return boolean 是否增加成功
     */
    @Override
    public boolean add(User user) {
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
