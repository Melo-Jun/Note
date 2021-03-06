package com.melo.notes.dao.inter;

import com.melo.notes.entity.Folder;
import com.melo.notes.entity.Group;

import java.util.HashMap;

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
     * @return HashMap 笔记分组id-笔记分组名称
     */
    HashMap<Object, Object> showNoteGroup(Folder folder);

    /**
     * 展示笔记分组名
     * @param group 笔记分组对象
     * @return String 笔记分组名
     */
     String showGroupName(Group group);

    /**
     * 删除分组
     * @param groupId 笔记分组id
     * @return int 影响的行数
     */
    int deleteGroup(String groupId);

    /**
     * 增加笔记分组
     * @param group 笔记分组对象
     * @return boolean 是否增加成功
     */
    boolean addGroup(Group group);

    /**
     * 更改笔记分组
     *
     * @param group 在service完成封装后的对象
     * @return int 影响的行数
     */
    int updateGroup(Group group);

    /**
     * 计算笔记分组数量
     * @param group 笔记分组对象
     * @return String 笔记分组数量
     */
     String countGroup(Group group);

    /**
     * 获取最大Id
     * @description 用于生成默认笔记分组(需获取最新加入进来的知识库id)id设置自增了
     * @notice 无最大时则返回1
     * @param obj 对象
     * @return String  该对象表中最大id
     */
     String getMaxId(Object obj);

}
