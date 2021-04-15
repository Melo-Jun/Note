package com.melo.notes.service.inter;

import com.melo.notes.entity.Forum;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description ��̳ҳ������߼��ӿ�
 * @date 2021-4-15 17:08
 */
public interface ForumService {

    /**
     * չʾ��̳����
     * @description Jlist��Ҫ�õ�
     * @return String[]
     */
     String[] listForumTitle();

    /**
     * չʾ��̳����
     * @param forumId ��̳id
     * @return LinkedList<Forum> ��̳��������
     */
     LinkedList<Forum> showForumText(String forumId);

    /**
     * ������̳����
     * @param title ����
     * @param text �ı�����
     * @return boolean �Ƿ���ӳɹ�
     */
     boolean addForum(String title,String text);

}
