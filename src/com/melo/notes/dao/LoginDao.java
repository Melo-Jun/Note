package com.melo.notes.dao;


import com.melo.notes.entity.User;
import com.melo.notes.impl.BaseDaoImpl;

import java.sql.*;

import static com.melo.notes.util.JdbcUtil.*;
import static com.melo.notes.util.Md5Utils.getDigest;


/**
 * @author Jun
 * @program Note
 * @description 登录页面相关数据库操作
 * @date 2021-3-27 12:16
 */
public class LoginDao extends BaseDaoImpl {

    /**
     * 注册新用户
     * @param user
     * @return boolean 是否成功注册
     */
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
    public boolean login(User user){
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select password from user where user_name=? ";
            ps=conn.prepareStatement(sql);
            ps.setString(1, user.getUserName() );
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
