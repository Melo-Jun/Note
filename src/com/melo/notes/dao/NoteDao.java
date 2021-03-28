package com.melo.notes.dao;

import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.impl.BaseDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.melo.notes.util.JdbcUtil.*;

/**
 * @author Jun
 * @program Note
 * @description 笔记类数据库操作
 * @date 2021-3-27 20:40
 */
public class NoteDao extends BaseDaoImpl {

    /**
     * 根据用户名列出笔记标题界面
     * @param user 用户
     * @return
     */
    public ResultSet listNoteTitle(User user){
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select title from note where author=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, user.getUserName() );
            rs=ps.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
        }
        return null;
    }

    /**
     * 通过点击标题查看笔记详情
     * @param title 笔记标题
     */
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
     */
    public void addNote(Note note){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into note values (id,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            setParam(ps,note);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            freeConnection(conn);
            close(ps, null);
        }
    }
}
