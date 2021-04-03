package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.FolderDao;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.melo.notes.util.JdbcUtils.*;

/**
 * @author Jun
 * @program Note
 * @description 知识库数据库操作实现类
 * @date 2021-3-20 9:04
 */
public class FolderDaoImpl extends BaseDaoImpl implements FolderDao  {

    /**
     * 本表查询所用到所有字段
     */
    private final String ALLCOLUMNNAME="id,folder_name,author_id,access";

    /**
     * 根据用户Id获取知识库名称
     * @param user 用户
     * @return ResultSet 结果集
     */
    @Override
    public HashMap<Object, Object> showFolderName(User user) {
        String sql="select id,folder_name from located_folder where author_id=?";
        return queryMap(sql,user);
    }

    /**
     * 根据知识库名删除知识库
     *
     * @param folderName 知识库名称
     * @return int 影响的行数
     */
    @Override
    public int deleteFolder(String folderName) {
        Folder folder = new Folder();
        folder.setFolderName(folderName);
        return delete(folder);
    }
}
