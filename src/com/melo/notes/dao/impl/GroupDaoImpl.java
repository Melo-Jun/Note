package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.GroupDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.melo.notes.util.JdbcUtils.freeConnection;
import static com.melo.notes.util.JdbcUtils.getConnection;

/**
 * @author Jun
 * @program Note
 * @description 笔记分组数据库操作实现类
 * @date 2021-3-20 9:06
 */
public class GroupDaoImpl implements GroupDao {
    /**
     * 根据知识库列出分组界面
     * @param folderName 知识库名称
     * @return ResultSet 结果集
     */
    @Override
    public ResultSet showNoteGroup(String folderName) {
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select group_name from located_group where located_folder=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, folderName);
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
