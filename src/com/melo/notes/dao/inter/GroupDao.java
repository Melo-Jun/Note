package com.melo.notes.dao.inter;

import java.sql.ResultSet;

/**
 * @author Jun
 * @program Note
 * @description 笔记分组数据库操作接口
 * @date 2021-3-20 9:00
 */
public interface GroupDao {
    /**
     * 根据知识库列出分组界面
     * @param folderName 知识库名称
     * @return ResultSet 结果集
     */
     ResultSet showNoteGroup(String folderName);

}
