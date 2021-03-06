package com.melo.notes.dao.inter;

import com.melo.notes.entity.Folder;
import com.melo.notes.entity.User;

import java.util.HashMap;

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
     * @return HashMap id和名称的键值对
     */
    HashMap<Object, Object> showFolderName(User user);


    /**
     * 增加知识库
     * @param folder 知识库对象
     * @return boolean 是否增加成功
     */
    boolean addFolder(Folder folder);


    /**
     * 根据知识库id删除知识库
     * @param folderId 知识库id
     * @return int 影响的行数
     */
     int deleteFolder(String folderId);

    /**
     * 更改知识库名称
     * @param folder 在service完成封装后的对象
     * @return int 影响的行数
     */
     int updateFolderName(Folder folder);
}
