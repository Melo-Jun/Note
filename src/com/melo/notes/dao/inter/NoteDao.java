package com.melo.notes.dao.inter;

import com.melo.notes.entity.Note;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 笔记类数据库操作接口
 * @date 2021-3-28 20:38
 */
public interface NoteDao {

    /**
     * 根据xxx列出笔记标题
     * @param obj 根据的对象
     * @notice 无根据时则传null
     * @return LinkedList 笔记标题链表
     */
     LinkedList<Object> showNoteTitle(Object obj);

    /**
     *分页查询文本
     * @param obj 根据对象
     * @return String 笔记内容
     */
    String showNoteText(Object obj);

    /**
     * 列出笔记所有信息(内容另外分页展示)
     * @param obj 根据的对象
     * @return linkedList 笔记信息链表
     */
    LinkedList<Object> showNoteAll(Object obj);



    /**
     * 新增笔记
     * @param note 笔记实体类
     * @return boolean 是否增加成功
     */
     boolean addNote(Note note);

    /**
     * 删除笔记
     * @param noteId 笔记id
     * @return int 影响的行数
     */
     int deleteNote(String noteId);

    /**
     * 修改笔记
     * @param note 笔记实体类
     * @return int 影响的行数
     */
     int updateNote(Note note);
}
