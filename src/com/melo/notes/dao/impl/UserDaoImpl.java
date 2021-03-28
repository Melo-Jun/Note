package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.UserDao;
import com.melo.notes.entity.User;
import com.melo.notes.exception.DaoException;
import com.melo.notes.dao.impl.BaseDaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.melo.notes.util.JdbcUtil.*;

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
    public boolean insert(User user){
        Connection conn = getConnection();
        PreparedStatement ps=null;
        String sql="insert into user values (id,?,?,photo)";
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
