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
     * 根据用户Id获取知识库名称
     * @param user 用户
     * @return ResultSet 结果集
     */
    ResultSet showNoteFolder(User user);


    /**
     * 根据知识库名删除知识库
     * @param folderName 知识库名称
     * @return int 影响的行数
     */
     int deleteFolder(String folderName);
}
