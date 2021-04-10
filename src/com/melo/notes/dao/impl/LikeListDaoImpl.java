package com.melo.notes.dao.impl;

import com.melo.notes.entity.LikeList;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 点赞记录类数据库操作实现类
 * @date 2021-4-10 11:16
 */
public class LikeListDaoImpl extends BaseDaoImpl{

    private final String TABLE_NAME="like_list";

    /**
     *
     * @param likeList
     * @return
     */
    public boolean addLikeList(LikeList likeList){
        return super.insert(likeList)==1;
    }

    public boolean deleteLikeList(LikeList likeList){
        return super.delete(likeList)==1;
    }

    public LinkedList showLikeUser(LikeList likeList){
        String sql="select user_id from "+TABLE_NAME+" where note_id=? ";
        return queryList(sql,likeList);
    }
}
