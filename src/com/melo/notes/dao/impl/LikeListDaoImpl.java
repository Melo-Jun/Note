package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.LikeListDao;
import com.melo.notes.entity.LikeList;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 点赞记录类数据库操作实现类
 * @date 2021-4-10 11:16
 */
public class LikeListDaoImpl extends BaseDaoImpl implements LikeListDao {

    private final String TABLE_NAME="like_list";

    /**
     *新增点赞记录
     * @param likeList 点赞记录对象
     * @return boolean 是否增加成功
     */
    @Override
    public boolean addLikeList(LikeList likeList){
        return super.insert(likeList)==1;
    }

    /**
     * 删除点赞记录
     * @param likeList 点赞记录对象
     * @return boolean 是否删除成功
     */
    @Override
    public boolean deleteLikeList(LikeList likeList){
        return super.delete(likeList)==1;
    }


    /**
     * 判断该用户是否点赞过
     * @param likeList 点赞记录对象
     * @return boolean 是否点赞过
     */
    @Override
    public boolean everLike(LikeList likeList){
        String sql="select id from "+TABLE_NAME+" where note_id =? and user_id =?";
        return !queryList(sql,likeList).isEmpty();
    }
}
