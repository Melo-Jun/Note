package com.melo.notes.service.inter;

import com.melo.notes.entity.Announcement;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 管理员登录页面相关逻辑接口
 * @date 2021-4-13 11:53
 */
public interface AdminService {

    /**
     * 验证是否为管理员
     * @param adminName 管理员名称
     * @param pass 密码
     * @return boolean 是否验证成功
     */
    boolean isAdmin(String adminName,String pass);

    /**
     * 新增公告
     * @param title 公告标题
     * @param text 公告内容
     * @return 是否新增成功
     */
     boolean addAnnouncement(String title,String text);

    /**
     * 展示管理员发布的所有公告
     * @return 公告链表
     */
     LinkedList<Announcement> showAnnouncementAll();
}
