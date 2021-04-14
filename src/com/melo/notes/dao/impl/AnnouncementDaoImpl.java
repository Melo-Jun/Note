package com.melo.notes.dao.impl;


import com.melo.notes.dao.inter.AnnouncementDao;
import com.melo.notes.entity.Announcement;

import java.util.LinkedList;


/**
 * @author Jun
 * @program Note
 * @description 公告数据库操作实现类
 * @date 2021-4-13 20:44
 */
public class AnnouncementDaoImpl extends BaseDaoImpl implements AnnouncementDao {

    /**
     * 该类对应表名
     */
    private final String TABLE_NAME="announcement";
    /**
     *本表对应所有字段
     */
    private final String ALL_FIELD_NAME="title,text";

    /**
     * 新增公告
     * @param announcement 相关公告对象
     * @return boolean 是否添加成功
     */
    @Override
    public boolean addAnnouncement(Announcement announcement){
        return super.insert(announcement)==1;
    }

    /**
     * 展示管理员发布的所有公告
     * @param announcement 相关公告对象
     * @return 公告链表
     */
    @Override
    public LinkedList showAnnouncementAll(Announcement announcement){
        String sql="select "+ALL_FIELD_NAME+" from " +TABLE_NAME;
        return super.queryAll(sql,announcement,Announcement.class);
    }
}
