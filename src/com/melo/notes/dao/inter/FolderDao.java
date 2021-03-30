package com.melo.notes.dao.inter;

import com.melo.notes.entity.User;

import java.sql.ResultSet;

/**
 * @author Jun
 * @program Note
 * @description 知识库数据库操作接口
 * @date 2021-3-20 9:02
 */
public interface FolderDao {

    /**
     * 根据用户名获取知识库名称
     * @param user 用户
     * @return ResultSet 结果集
     */
    ResultSet showNoteFolder(User user);

    /**
     * 根据用户名查找知识库
     * @param user
     * @return
     */
    ResultSet searchNoteFolder(User user);
}
