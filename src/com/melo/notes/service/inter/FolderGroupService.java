package com.melo.notes.service.inter;

import com.melo.notes.entity.User;

import java.sql.ResultSet;

/**
 * @author Jun
 * @program Note
 * @description 设置笔记知识库分组业务逻辑接口
 * @date 2021-4-1 16:09
 */
public interface FolderGroupService {

    /**
     * 根据节点数判断是什么类
     * @param TreePathCount
     * @return String 对象名称
     */
    String judgeClass(int TreePathCount);

    /**
     * 根据用户名获取知识库名称
     * @param user 用户
     * @return ResultSet 结果集
     */
     ResultSet showNoteFolder(User user);

    /**
     * 根据知识库列出分组界面
     * @param folderId 知识库Id
     * @return ResultSet 结果集
     */
     ResultSet showNoteGroup(String folderId);

    /**
     * 根据传入类名删除对应类对象
     *
     * @param selectedName 对象名称
     * @param selectedClassName 对应类
     * @return int 影响的行数
     */
     int delete(String selectedName,String selectedClassName);

    /**
     * 选择分组时判断是否为分组
     * @param selectedClassName 选中的对应类
     * @return
     */
     boolean isGroup(String selectedClassName);

    /**
     * 根据名称获取id
     * @param name 名称
     * @return String id
     */
    String getId(String name);
}
