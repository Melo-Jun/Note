package com.melo.notes.service.inter;

import com.melo.notes.entity.Note;

import java.util.LinkedList;
import java.util.Vector;

/**
 * @author Jun
 * @program Note
 * @description 表格页面相关逻辑接口
 * @date 2021-4-8 16:16
 */
public interface NoteTableService {

    /**
     * 提取笔记属性填充表格
     * @param tempNote 临时的笔记对象
     * @return 属性值集合
     */
     Vector<Object> fillTable(Note tempNote);

    /**
     * 获取点赞过该笔记的用户
     * @param noteId 笔记id
     * @return 用户链表
     */
     LinkedList showLikeUser(String noteId);

    /**
     * 更新点赞数
     * @param updateLikeCount 更改后的点赞数
     * @param noteId 笔记id
     * @return 是否操作成功
     */
     boolean updateLikeCount(String updateLikeCount,String noteId);

    /**
     * 点赞
     * @param updateLikeCount 更改后的点赞数
     * @param noteId 笔记id
     * @return 操作是否成功
     */
    boolean increaseLikeCount(String updateLikeCount, String noteId);

    /**
     * 取消点赞
     * @param updateLikeCount 更改后的点赞数
     * @param noteId 笔记id
     * @return 操作是否成功
     */
     boolean decreaseLikeCount(String updateLikeCount, String noteId);

    /**
     * 展示笔记作者名
     * @param authorId 作者id
     * @return 作者名
     */
     String showNoteAuthor(String authorId);

    /**
     * 展示笔记分组名
     * @param groupId 笔记id
     * @return String 笔记分组名
     */
     String showGroupName(String groupId);
}
