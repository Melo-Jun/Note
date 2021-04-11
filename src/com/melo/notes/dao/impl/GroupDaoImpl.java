package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.GroupDao;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
     * @param folder 知识库对象
     * @return HashMap 笔记分组id-笔记分组名称
     */
    @Override
    public HashMap<Object, Object> showNoteGroup(Folder folder) {
        String sql="select id,group_name from "+TABLE_NAME+" where located_folder=?";
        return queryMap(sql,folder);
    }

    /**
     * 展示笔记分组名
     * @param group
     * @return
     */
    @Override
    public String showGroupName(Group group){
        String sql="select group_name from " +TABLE_NAME+" where id=?";
        return queryList(sql,group).getFirst().toString();
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
     * 增加笔记分组
     * @param group 笔记分组对象
     * @return boolean 是否增加成功
     */
    @Override
    public boolean addFolder(Group group){
        group.setId(getMaxId(group));
        return insert(group)==1;
    }

    /**
     * 更改笔记分组名称
     *
     * @param group 在service完成封装后的对象
     * @return
     */
    @Override
    public int updateGroup(Group group) {
        return super.update(group);
    }

    public String countGroup(Group group){
        String sql="select count(*) from " +TABLE_NAME+" where author_id=?";
        return queryList(sql,group).getFirst().toString();
    }


}
