package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.AdminDaoImpl;
import com.melo.notes.dao.impl.AnnouncementDaoImpl;
import com.melo.notes.entity.Admin;
import com.melo.notes.entity.Announcement;
import com.melo.notes.service.inter.AdminService;
import com.melo.notes.util.BeanFactory;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description ����Ա��¼ҳ������߼�ʵ����
 * @date 2021-4-13 11:53
 */
public  class AdminServiceImpl extends BaseServiceImpl implements AdminService {
    /**
     * ��ز��������
     */
    AdminDaoImpl adminDao=(AdminDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.AdminDao);
    AnnouncementDaoImpl announcementDao=(AnnouncementDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.AnnouncementDao);

    /**
     * ��֤�Ƿ�Ϊ����Ա
     * @param adminName ����Ա����
     * @param pass ����
     * @return boolean �Ƿ���֤�ɹ�
     */
    @Override
    public boolean isAdmin(String adminName, String pass){
        if(super.notNull(adminName,pass)) {
            Admin admin = new Admin();
            admin.setAdminName(adminName);
            admin.setPassword(pass);
            return adminDao.isAdmin(admin);
        }
        return false;
    }

    /**
     * ��������
     * @param title �������
     * @param text ��������
     * @return �Ƿ������ɹ�
     */
    @Override
    public boolean addAnnouncement(String title, String text){
        if(super.notNull(title,text)) {
            Announcement announcement = new Announcement(title, text);
            return announcementDao.addAnnouncement(announcement);
        }
        return false;
    }

    /**
     * չʾ����Ա���������й���
     * @return ��������
     */
    @Override
    public LinkedList<Announcement> showAnnouncementAll(){
        Announcement announcement = new Announcement();
        return announcementDao.showAnnouncementAll(announcement);
    }
}
