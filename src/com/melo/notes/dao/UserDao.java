package com.melo.notes.dao;

import com.melo.notes.entity.User;
import com.melo.notes.exception.DaoException;
import com.melo.notes.impl.BaseDaoImpl;
import com.melo.notes.inter.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.melo.notes.util.JdbcUtil.*;

/**
 * @author Jun
 * @program Note
 * @description 用户类数据库操作
 * @date 2021-3-27 12:16
 */
public class UserDao extends BaseDaoImpl {

    /**
     * 增加用户
     * @param user
     * @return boolean 是否增加成功
     */
    public boolean insert(User user){
        Connection conn = getConnection();
        PreparedStatement ps=null;
        String sql="insert into user values (id,?,?)";
        try{
            ps=conn.prepareStatement(sql);
            setParam(ps,user);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        } catch (SQLException | DaoException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(ps,null);
        }
        return false;
    }
}
