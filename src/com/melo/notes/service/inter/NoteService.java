package com.melo.notes.service.inter;


import com.melo.notes.entity.Note;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 展示标题页面相关逻辑接口
 * @date 2021-4-3 20:24
 */
public interface NoteService {

    /**
     * 根据笔记分组id填充标题
     * @description 用在嵌套生成JTree时
     * @param groupId 笔记分组id
     * @return HashMap id-name键值对
     */
     HashMap showNoteTitle(String groupId);

    /**
     *展示笔记详情内容(Text另外单独分页)
     * @param obj 相关对象
     * @return LinkedList 笔记所有值链表
     */
    LinkedList showNoteAll(Object obj);


    /**
     * 根据笔记id展示笔记文本内容
     *
     * @param noteId 笔记id
     * @return String 笔记文本内容
     */
    String showNoteText(String noteId);

    /**
     * 更改笔记内容
     * @param title 标题
     * @param text 文本内容
     * @param access 权限
     * @param noteId 笔记id
     * @return int 影响的行数
     */
    public int updateNote(String noteId,String title,String text,String access);

    /**
     * 删除笔记
     * @param noteId 笔记id
     * @return int 影响的行数
     */
     int delete(String noteId);
}
