package com.melo.notes.service.inter;

import com.melo.notes.entity.Forum;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 论坛页面相关逻辑接口
 * @date 2021-4-15 17:08
 */
public interface ForumService {

    /**
     * 展示论坛标题
     * @description Jlist需要用到
     * @return String[]
     */
     String[] listForumTitle();

    /**
     * 展示论坛内容
     * @param forumId 论坛id
     * @return LinkedList<Forum> 论坛对象链表
     */
     LinkedList<Forum> showForumText(String forumId);

    /**
     * 新增论坛内容
     * @param title 标题
     * @param text 文本内容
     * @return boolean 是否添加成功
     */
     boolean addForum(String title,String text);

}
