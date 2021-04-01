package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.FolderDao;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.melo.notes.util.JdbcUtils.*;

/**
 * @author Jun
 * @program Note
 * @description 知识库数据库操作实现类
 * @date 2021-3-20 9:04
 */
public class FolderDaoImpl extends BaseDaoImpl implements FolderDao  {

    /**
     * 根据用户Id获取知识库名称
     * @param user 用户
     * @return ResultSet 结果集
     */
    @Override
    public ResultSet showNoteFolder(User user) {
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select * from located_folder where author_id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, user.getId() );
            rs=ps.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
        }
        return null;
    }

    /**
     * 根据知识库名删除知识库
     *
     * @param folderName 知识库名称
     * @return int 影响的行数
     */
    @Override
    public int deleteFolder(String folderName) {
        Folder folder = new Folder();
        folder.setFolderName(folderName);
        return delete(folder);
    }
}
