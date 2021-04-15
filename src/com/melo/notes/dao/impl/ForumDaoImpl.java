package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.ForumDao;
import com.melo.notes.entity.Forum;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 论坛数据库操作实现类
 * @date 2021-4-15 15:52
 */
public class ForumDaoImpl extends BaseDaoImpl implements ForumDao {

    /**
     * 该类对应表名
     */
    private final String TABLE_NAME="forum";
    /**
     *本表对应所有字段
     */
    private final String ALL_FIELD_NAME="id,title,text,user_id";

    /**
     * 展示所有论坛信息
     * @param forum 根据的论坛对象(填充where子句)
     * @return 论坛对象链表
     */
    @Override
    public LinkedList<Forum> showForumAll(Forum forum) {
        StringBuilder sql=new StringBuilder("select "+ ALL_FIELD_NAME+ " from " + TABLE_NAME);
        super.appendWhereToSql(sql,forum);
        return super.queryAll(sql.toString(),forum,Forum.class);
    }

    /**
     * 新增论坛
     * @param forum 论坛对象
     * @return 是否新增成功
     */
    @Override
    public boolean addForum(Forum forum) {
        return super.insert(forum)==1;
    }

}
