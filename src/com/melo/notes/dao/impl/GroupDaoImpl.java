package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.GroupDao;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.Group;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static com.melo.notes.util.JdbcUtils.*;
import static com.melo.notes.util.JdbcUtils.close;


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
     * @param group 笔记分组对象
     * @return String 笔记分组名
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
    public boolean addGroup(Group group){
        return insert(group)==1;
    }

    /**
     * 更改笔记分组名称
     *
     * @param group 在service完成封装后的对象
     * @return int 影响的行数
     */
    @Override
    public int updateGroup(Group group) {
        return super.update(group);
    }

    /**
     * 计算笔记分组数量
     * @param group 笔记分组对象
     * @return String 笔记分组数量
     */
    @Override
    public String countGroup(Group group){
        String sql="select count(*) from " +TABLE_NAME+" where author_id=?";
        return queryList(sql,group).getFirst().toString();
    }

    /**
     * 获取最大Id
     * 用于生成默认笔记分组(需获取最新加入进来的知识库)id设置自增了
     * @description
     * @notice 无最大时则返回1
     * @param obj 对象
     * @return String  该对象表中最大id
     */
    @Override
    public String getMaxId(Object obj) {
        String sql = "select MAX(id) from " + getTableName(obj);
        Connection conn=getConnection();
        Statement stmt=null;
        ResultSet rs=null;
        try {
            assert conn != null;
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            if(rs.next()){
                /*
                  获取最大后+1
                 */
                return rs.getObject(1)==null?"1":rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(stmt,rs);
        }
        return "1";
    }

}
