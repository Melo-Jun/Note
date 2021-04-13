package com.melo.notes.dao.inter;


import com.melo.notes.entity.Announcement;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 公告数据库操作接口
 * @date 2021-4-13 20:44
 */
public interface AnnouncementDao {

    /**
     * 新增公告
     * @param announcement 相关公告对象
     * @return boolean 是否添加成功
     */
     boolean addAnnouncement(Announcement announcement);


     /**
     * 展示管理员发布的所有公告
     * @param announcement 相关公告对象
     * @return 公告链表
     */
     LinkedList showAnnouncementAll(Announcement announcement);
}
