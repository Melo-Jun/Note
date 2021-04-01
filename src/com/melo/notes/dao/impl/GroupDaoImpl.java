package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.GroupDao;
import com.melo.notes.entity.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.melo.notes.util.JdbcUtils.*;

/**
 * @author Jun
 * @program Note
 * @description 笔记分组数据库操作实现类
 * @date 2021-3-20 9:06
 */
public class GroupDaoImpl extends BaseDaoImpl implements GroupDao {
    /**
     * 根据知识库列出分组界面
     * @param folderId 知识库Id
     * @return ResultSet 结果集
     */
    @Override
    public ResultSet showNoteGroup(String folderId) {
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select group_name from located_group where located_folder=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, folderId);
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
     * 删除分组
     * @param groupName 笔记分组名称
     * @return int 影响的行数
     */
    @Override
    public int deleteGroup(String groupName) {
        Group group = new Group();
        group.setGroupName(groupName);
        return delete(group);
    }

    /**
     * 根据名称获取id
     * @param name 名称
     * @return String id
     */
    @Override
    public String getId(String name) {
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select id from located_group where group_name=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, name);
            rs=ps.executeQuery();
            rs.next();
            return rs.getString("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(ps,rs);
        }
        return null;
    }

}
