package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.FolderDao;
import com.melo.notes.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.melo.notes.util.JdbcUtil.freeConnection;
import static com.melo.notes.util.JdbcUtil.getConnection;

/**
 * @author Jun
 * @program Note
 * @description 知识库数据库操作实现类
 * @date 2021-3-20 9:04
 */
public class FolderDaoImpl implements FolderDao {

    /**
     * 根据用户名列出知识库界面
     * @param user 用户
     * @return ResultSet 结果集
     */
    @Override
    public ResultSet showNoteFolder(User user) {
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select folder_name from located_folder where author=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, user.getUserName() );
            rs=ps.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
        }
        return null;
    }
}
