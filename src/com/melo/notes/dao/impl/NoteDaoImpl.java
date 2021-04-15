package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.NoteDao;
import com.melo.notes.entity.Note;

import java.util.HashMap;
import java.util.LinkedList;
;

/**
 * @author Jun
 * @program Note
 * @description 笔记类数据库操作实现类
 * @date 2021-3-28 20:40
 */
public class NoteDaoImpl extends BaseDaoImpl implements NoteDao {
    /**
     * 该类对应表名
     */
    private final String TABLE_NAME="note";
    /**
     *本表对应所有字段
     */
    private final String ALL_FIELD_NAME="id,title,author_id,text,access,like_count,located_group";

    /**
     * 根据xxx列出笔记标题
     * @param obj 根据的对象
     * @notice 无根据时则传null
     * @return LinkedList 笔记标题链表
     */
    @Override
    public HashMap showNoteTitle(Object obj) {
        StringBuilder sql = new StringBuilder( "select id,title from "+TABLE_NAME);
        /*
          完成sql注入和执行
         */
        super.appendWhereToSql(sql,obj);
        return queryMap(sql.toString(),obj);
    }

    /**
     *分页查询文本
     * @param obj 根据对象
     * @return String 笔记内容
     */
    @Override
    public String showNoteText(Object obj) {
        String sql="select text from "+TABLE_NAME+" where id=? ";
        return queryList(sql,obj).getFirst().toString();
    }

    /**
     * 列出笔记所有信息(内容另外分页展示)
     * @param obj 根据的对象
     * @return linkedList 笔记信息链表
     */
    @Override
    public LinkedList showNoteAll(Object obj) {
        StringBuilder sql = new StringBuilder( "select "+ALL_FIELD_NAME+" from "+TABLE_NAME);
        /*
          完成sql注入和执行
         */
        super.appendWhereToSql(sql,obj);
        return queryAll(sql.toString(),obj,Note.class);
    }

    /**
     * 新增笔记
     * @param note 笔记实体类
     * @return boolean 是否增加成功
     */
    @Override
    public boolean addNote(Note note){
        return super.insert(note)==1;
    }

    /**
     * 删除笔记
     * @param noteId 笔记id
     * @return int 影响的行数
     */
    @Override
    public int deleteNote(String noteId){
        Note note = new Note();
        note.setId(noteId);
        return super.delete(note);
    }

    /**
     * 修改笔记
     * @param note 笔记实体类
     * @return int 影响的行数
     */
    @Override
    public int updateNote(Note note) {
        return super.update(note);
    }
}
