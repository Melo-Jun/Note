package com.melo.notes.service.inter;

import com.melo.notes.entity.Announcement;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description ����Ա��¼ҳ������߼��ӿ�
 * @date 2021-4-13 11:53
 */
public interface AdminService {

    /**
     * ��֤�Ƿ�Ϊ����Ա
     * @param adminName ����Ա����
     * @param pass ����
     * @return boolean �Ƿ���֤�ɹ�
     */
    boolean isAdmin(String adminName,String pass);

    /**
     * ��������
     * @param title �������
     * @param text ��������
     * @return �Ƿ������ɹ�
     */
     boolean addAnnouncement(String title,String text);

    /**
     * չʾ����Ա���������й���
     * @return ��������
     */
     LinkedList<Announcement> showAnnouncementAll();
}
