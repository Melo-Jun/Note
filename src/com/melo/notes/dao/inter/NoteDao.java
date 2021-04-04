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
     * @return
     */
     LinkedList<Object> showNoteTitle(Object obj);

    /**
     * 通过点击标题查看按钮查看笔记详情
     * @param obj 根据的对象
     * @return
     */
    LinkedList<Object> listNoteAll(Object obj);

    /**
     * 分页查询文本
     * @param obj
     * @return
     */
    String showNoteText(Object obj);

    /**
     * 新增笔记
     * @param note 笔记实体类
     * @return
     */
     boolean addNote(Note note);
}
