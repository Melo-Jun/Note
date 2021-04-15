package com.melo.notes.dao.inter;

import com.melo.notes.entity.LikeList;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 点赞记录类数据库操作接口
 * @date 2021-4-10 11:00
 */
public interface LikeListDao {

    /**
     *新增点赞记录
     * @param likeList 点赞记录对象
     * @return boolean 是否增加成功
     */
     boolean addLikeList(LikeList likeList);

    /**
     * 删除点赞记录
     * @param likeList 点赞记录对象
     * @return boolean 是否删除成功
     */
     boolean deleteLikeList(LikeList likeList);

    /**
     * 判断该用户是否点赞过
     * @param likeList 点赞记录对象
     * @return boolean 是否点赞过
     */
     boolean everLike(LikeList likeList);
}
