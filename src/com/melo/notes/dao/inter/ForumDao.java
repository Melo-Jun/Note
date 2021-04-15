package com.melo.notes.dao.inter;

import com.melo.notes.entity.Forum;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 论坛数据库操作接口
 * @date 2021-4-15 15:52
 */
public interface ForumDao {

    /**
     * 展示所有论坛信息
     * @param forum 根据的论坛对象(填充where子句)
     * @return 论坛对象链表
     */
     LinkedList<Forum> showForumAll(Forum forum);

    /**
     * 新增论坛
     * @param forum 论坛对象
     * @return 是否新增成功
     */
     boolean addForum(Forum forum);
}
