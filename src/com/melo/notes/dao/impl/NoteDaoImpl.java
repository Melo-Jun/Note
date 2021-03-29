package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.NoteDao;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.dao.impl.BaseDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.melo.notes.util.JdbcUtil.*;

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
     * 根据用户名列出知识库界面
     * @param user 用户
     * @return ResultSet 结果集
     */
    @Override
    public ResultSet showNoteFolder(User user) {
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select folder_name from located_folder where author=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, user.getUserName() );
            rs=ps.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
        }
        return null;
    }

    /**
     * 根据知识库列出分组界面
     * @param folderName 知识库名称
     * @return ResultSet 结果集
     */
    @Override
    public ResultSet showNoteGroup(String folderName) {
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select group_name from located_group where located_folder=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, folderName);
            rs=ps.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
        }
        return null;
    }

    /**
     * 根据用户名列出笔记标题界面
     * @param groupName 分组名称
     * @return ResultSet 结果集
     */
    @Override
    public ResultSet showNoteTitle(String groupName){
        Connection conn= getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select title from note where located_group=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, groupName);
            rs=ps.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
        }
        return null;
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
     */
    @Override
    public void addNote(Note note){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "insert into note values (id,?,?,?,?,create_time,update_time,?,?)";
            ps = conn.prepareStatement(sql);
            setParam(ps,note);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            freeConnection(conn);
            close(ps, null);
        }
    }
}
