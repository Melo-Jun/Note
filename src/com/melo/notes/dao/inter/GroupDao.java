package com.melo.notes.dao.inter;

import com.melo.notes.entity.Folder;
import com.melo.notes.entity.Group;

import java.sql.ResultSet;
import java.util.HashMap;
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
     * @param folder 知识库对象
     * @return LinkedList<Object> 结果集链表
     */
    HashMap<Object, Object> showNoteGroup(Folder folder);

    /**
     * 删除分组
     * @param group 笔记分组对象
     * @return int 影响的行数
     */
    int deleteGroup(Group group);

    /**
     * 增加笔记分组
     * @param group 笔记分组对象
     * @return boolean 是否增加成功
     */
    boolean addFolder(Group group);

    /**
     * 更改知识库名称
     * @param group 在service完成封装后的对象
     * @return int 影响的行数
     */
    int updateGroupName(Group group);

}
