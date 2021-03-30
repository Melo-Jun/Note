package com.melo.notes.dao.impl;


import com.melo.notes.dao.inter.LoginDao;
import com.melo.notes.entity.User;

import java.sql.*;

import static com.melo.notes.util.JdbcUtils.*;
import static com.melo.notes.util.Md5Utils.getDigest;


/**
 * @author Jun
 * @program Note
 * @description 登录页面相关数据库操作实现类
 * @date 2021-3-28 20:36
 */
public class LoginDaoImpl extends BaseDaoImpl implements LoginDao {

    /**
     * 注册新用户
     * @param user
     * @return boolean 是否成功注册
     */
    @Override
    public boolean register(User user){
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select user_name from user where user_name=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, user.getUserName() );
            rs=ps.executeQuery();
            if(rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(ps,rs);
        }
        return true;
    }


     /**
     * 密码验证
     * @param user
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     * @return
     */
    @Override
    public boolean login(User user){
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select password from user where user_name=? ";
            ps=conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            rs=ps.executeQuery();
            if(rs.next()){
                String uPass=rs.getString("password");
                if(getDigest(user.getPassword()).equals(uPass)){
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(ps,rs);
        }
        return false;
    }


}
