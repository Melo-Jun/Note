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
 * @description ��̳ҳ������߼�ʵ����
 * @date 2021-4-15 17:15
 */
public class ForumServiceImpl extends BaseServiceImpl implements ForumService {

    ForumDaoImpl forumDao=(ForumDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.ForumDao);

    /**
     * չʾ��̳����
     * @description Jlist��Ҫ�õ�
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
     * չʾ��̳����
     * @param forumId ��̳id
     * @return LinkedList<Forum> ��̳��������
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
     * ������̳����
     * @param title ����
     * @param text �ı�����
     * @return boolean �Ƿ���ӳɹ�
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
