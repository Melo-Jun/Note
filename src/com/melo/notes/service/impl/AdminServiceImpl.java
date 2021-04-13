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
 * @description 管理员登录页面相关逻辑实现类
 * @date 2021-4-13 11:53
 */
public  class AdminServiceImpl extends BaseServiceImpl implements AdminService {
    /**
     * 相关操作类对象
     */
    AdminDaoImpl adminDao=(AdminDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.AdminDao);
    AnnouncementDaoImpl announcementDao=(AnnouncementDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.AnnouncementDao);

    /**
     * 验证是否为管理员
     * @param adminName 管理员名称
     * @param pass 密码
     * @return boolean 是否验证成功
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
     * 新增公告
     * @param title 公告标题
     * @param text 公告内容
     * @return 是否新增成功
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
     * 展示管理员发布的所有公告
     * @return 公告链表
     */
    @Override
    public LinkedList<Announcement> showAnnouncementAll(){
        Announcement announcement = new Announcement();
        return announcementDao.showAnnouncementAll(announcement);
    }
}
