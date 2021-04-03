package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.NoteDao;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static com.melo.notes.util.JdbcUtils.*;

/**
 * @author Jun
 * @program Note
 * @description 笔记类数据库操作实现类
 * @date 2021-3-28 20:40
 */
public class NoteDaoImpl extends BaseDaoImpl implements NoteDao {

    public static Object instance(){
        return new NoteDaoImpl();
    }

    /**
     * 根据xxx列出笔记标题
     * @param obj 根据的对象
     * @return
     */
    @Override
    public LinkedList<Object> showNoteTitle(Object obj) {
        String sql = "select title from note ";
        User user = new User();
        return queryList(sql,null);
    }

    /**
     * 通过点击标题查看笔记详情
     * @param title 笔记标题
     */
    @Override
    public void listNoteText(String title) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select text from note where title=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getObject("text"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            freeConnection(conn);
            close(ps, rs);
        }
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
