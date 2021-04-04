package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.NoteDao;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static com.melo.notes.util.JdbcUtils.*;
import static com.melo.notes.util.ReflectUtils.getFields;
import static com.melo.notes.util.ReflectUtils.getMethods;

/**
 * @author Jun
 * @program Note
 * @description 笔记类数据库操作实现类
 * @date 2021-3-28 20:40
 */
public class NoteDaoImpl extends BaseDaoImpl implements NoteDao {

    private final String TABLE_NAME="note";

    private final String ALL_FIELD_NAME="id,title,author_id,text,access,gmt_create,gmt_modified,like_count,located_group";

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
        StringBuilder sql = new StringBuilder( "select title from "+TABLE_NAME);
        /**
         * 将对象映射成属性和值(属性会映射为数据库字段名)
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);
        fieldMapper(obj,fieldNames,fieldValues);
        /**
         * 将字段名填入sql语句
         */
        if(obj!=null){
            sql.append(" where "+fieldNames.getFirst()+" ="+"?");
        }
        /**
         * 完成sql注入和执行
         */
        return queryList(sql.toString(),obj);
    }

    /**
     * 通过点击标题查看按钮查看笔记详情
     * @param title 笔记标题
     */
    @Override
    public void listNoteText(String title) {
        String sql="select "+ALL_FIELD_NAME+" from "+TABLE_NAME;
    }

    /**
     * 新增笔记
     * @param note 笔记实体类
     * @return
     */
    @Override
    public boolean addNote(Note note){
        return super.insert(note)==1;
    }
}
