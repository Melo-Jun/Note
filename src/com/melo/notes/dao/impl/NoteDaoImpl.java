package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.NoteDao;
import com.melo.notes.entity.Note;

import java.util.LinkedList;
;

/**
 * @author Jun
 * @program Note
 * @description 笔记类数据库操作实现类
 * @date 2021-3-28 20:40
 */
public class NoteDaoImpl extends BaseDaoImpl implements NoteDao {

    private final String TABLE_NAME="note";

    private final String ALL_FIELD_NAME="id,title,author_id,text,access,like_count,located_group";

    public static Object instance(){
        return new NoteDaoImpl();
    }

    /**
     * 根据xxx列出笔记标题
     * @param obj 根据的对象
     * @notice 无根据时则传null
     * @return
     */
    @Override
    public LinkedList<Object> showNoteTitle(Object obj) {
        StringBuilder sql = new StringBuilder( "select title from "+TABLE_NAME+" where access=? ");
        /**
         * 将对象映射成属性和值(属性会映射为数据库字段名)
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);
        /**
         * 将字段名填入sql语句
         * 不等于1说明还有其他根据条件,则动态增加条件
         */
        if(fieldValues.size()!=1){
            sql.append(" AND "+fieldNames.getLast()+" ="+"?");
        }
        System.out.println(sql.toString());
        /**
         * 完成sql注入和执行
         */
        return queryList(sql.toString(),obj);
    }

    /**
     *分页查询文本
     * @param obj
     * @return
     */
    @Override
    public String showNoteText(Object obj) {
        String sql="select text from "+TABLE_NAME+" where title=? ";
        return queryList(sql,obj).getFirst().toString();
    }

    /**
     * 通过点击标题查看按钮查看笔记详情
     * @param obj 根据的对象
     * @return
     */
    @Override
    public LinkedList<Object> listNoteAll(Object obj) {
        String sql = "select "+ALL_FIELD_NAME+" from "+TABLE_NAME+" where title=? ";
        /**
         * 将对象映射成属性和值(属性会映射为数据库字段名)
         */
       /* LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);*/
        /**
         * 完成sql注入和执行
         */
        System.out.println(sql);
        return queryAll(sql,obj,Note.class);
    }

    /**
     * 新增笔记
     * @param note 笔记实体类
     * @return boolean 是否增加成功
     */
    @Override
    public boolean addNote(Note note){
        note.setId(getMaxId(note));
        return super.insert(note)==1;
    }
}
