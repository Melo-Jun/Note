package com.melo.notes.dao.inter;

import java.sql.ResultSet;
import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 笔记分组数据库操作接口
 * @date 2021-3-20 9:00
 */
public interface GroupDao {
    /**
     * 根据知识库id列出分组界面
     * @param folderId 知识库id
     * @return LinkedList<Object> 结果集链表
     */
    LinkedList<Object> showNoteGroup(String folderId);

    /**
     * 删除分组
     * @param groupName 笔记分组名称
     * @return int 影响的行数
     */
    int deleteGroup(String groupName);

    /**
     * 根据名称获取id
     * @param name 名称
     * @return String id
     */
    String getId(String name);
}
