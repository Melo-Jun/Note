package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.GroupDao;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static com.melo.notes.util.JdbcUtils.*;

/**
 * @author Jun
 * @program Note
 * @description 笔记分组数据库操作实现类
 * @date 2021-3-20 9:06
 */
public class GroupDaoImpl extends BaseDaoImpl implements GroupDao {

    private final String TABLE_NAME="located_group";

    /**
     * 根据知识库id列出分组界面
     * @param folderId 知识库id
     * @return LinkedList<Object> 结果集链表
     */
    @Override
    public LinkedList<Object> showNoteGroup(String folderId) {
        String sql="select group_name from "+TABLE_NAME+" where located_folder=?";
        Folder folder = new Folder();
        folder.setId(folderId);
        return queryList(sql,folder);
    }

    /**
     * 删除分组
     * @param groupId 笔记分组id
     * @return int 影响的行数
     */
    @Override
    public int deleteGroup(String groupId) {
        Group group = new Group();
        group.setId(groupId);
        return delete(group);
    }

    /**
     * 更改知识库名称
     *
     * @param group 在service完成封装后的对象
     * @return
     */
    @Override
    public int updateGroupName(Group group) {
        return update(group);
    }


}
