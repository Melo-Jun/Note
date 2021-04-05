package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.FolderDao;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.User;
import com.melo.notes.view.FolderView;

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
     * 本表查询所用到所有字段以及表名
     */
    private final String ALLCOLUMNNAME="id,folder_name,author_id,access";
    private final String TABLE_NAME="located_folder";

    /**
     * 根据用户Id获取知识库名称
     * @param user 用户
     * @return HashMap id和名称的键值对
     */
    @Override
    public HashMap<Object, Object> showFolderName(User user) {
        String sql="select id,folder_name from "+TABLE_NAME+" where author_id=?";
        return queryMap(sql,user);
    }

    public int addFolder(Folder folder){
        return insert(folder);
    }

    /**
     * 根据知识库id删除知识库
     *
     * @param folderId 知识库id
     * @return int 影响的行数
     */
    @Override
    public int deleteFolder(String folderId) {
        Folder folder = new Folder();
        folder.setId(folderId);
        return delete(folder);
    }

    /**
     * 更改知识库名称
     * @param folder 在service完成封装后的对象
     * @return
     */
    @Override
    public int updateFolderName(Folder folder) {
        return update(folder);
    }


}
