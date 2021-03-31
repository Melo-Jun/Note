package com.melo.notes.dao.inter;

import com.melo.notes.entity.Note;

import java.sql.ResultSet;

/**
 * @author Jun
 * @program Note
 * @description 笔记类数据库操作接口
 * @date 2021-3-28 20:38
 */
public interface NoteDao {

    /**
     * 根据用户名列出笔记标题界面
     * @param groupName 分组名称
     * @return
     */
     ResultSet showNoteTitle(String groupName);

    /**
     * 通过点击标题查看笔记详情
     * @param title 笔记标题
     */
     void listNoteText(String title);

    /**
     * 新增笔记
     * @param note 笔记实体类
     * @return
     */
     boolean addNote(Note note);
}
