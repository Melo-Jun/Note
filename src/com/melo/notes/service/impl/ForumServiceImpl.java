package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.ForumDaoImpl;
import com.melo.notes.entity.Forum;
import com.melo.notes.service.inter.ForumService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.LoginView;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 论坛页面相关逻辑实现类
 * @date 2021-4-15 17:15
 */
public class ForumServiceImpl extends BaseServiceImpl implements ForumService {

    ForumDaoImpl forumDao=(ForumDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.ForumDao);

    /**
     * 展示论坛标题
     * @description Jlist需要用到
     * @return String[]
     */
    @Override
    public String[] listForumTitle(){
        LinkedList<Forum> forums = forumDao.showForumAll(new Forum());
        int count=forums.size();
        int index=0;
        String[] titles = new String[count];
        for(Forum forum:forums){
            titles[index]=forum.getId()+":"+forum.getTitle();
            index++;
        }
        return titles;
    }

    /**
     * 展示论坛内容
     * @param forumId 论坛id
     * @return LinkedList<Forum> 论坛对象链表
     */
    @Override
    public LinkedList<Forum> showForumText(String forumId){
        if(super.notNull(forumId)) {
            Forum forum = new Forum();
            forum.setId(forumId);
            return forumDao.showForumAll(forum);
        }
        return null;
    }

    /**
     * 新增论坛内容
     * @param title 标题
     * @param text 文本内容
     * @return boolean 是否添加成功
     */
    @Override
    public boolean addForum(String title, String text){
        if(super.notNull(title,text)) {
            Forum forum = new Forum();
            forum.setTitle(title);
            forum.setText(text);
            forum.setUserId(LoginView.USER.getId());
            return forumDao.addForum(forum) ;
        }
        return false;
    }
}
