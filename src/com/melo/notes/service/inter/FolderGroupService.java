package com.melo.notes.service.inter;

import com.melo.notes.entity.User;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 设置笔记知识库分组业务逻辑接口
 * @date 2021-4-1 16:09
 */
public interface FolderGroupService {

    /**
     * 根据节点数判断是什么类
     * @param treePathCount 节点数
     * @return String 对象名称
     */
    String judgeType(int treePathCount);

    /**
     * 根据登录的用户id获取知识库名称
     * @return HashMap 知识库id-知识库名称
     */
     HashMap<Object, Object> showFolderName();

    /**
     * 根据知识库列出分组界面
     * @param folderId 笔记分组Id
     * @return HashMap 笔记分组id-笔记分组名称
     */
    HashMap<Object, Object> showNoteGroup(String folderId);

    /**
     * 根据传入类名删除对应类对象
     *
     * @param selectedName      对象名称
     * @param selectedType 对应类型
     * @return int 影响的行数
     */
     int delete(String selectedName,String selectedType);

    /**
     * 新增知识库
     * @param name
     * @param access
     * @param authorId
     * @return
     */
     boolean addFolder(String name, String access,String authorId);

    /**
     * 新增笔记分组
     * @param name
     * @param locatedFolder
     * @return
     */
     boolean addGroup(String name,String locatedFolder);

    /**
     * 根据前台传入参数更新相应对象名称
     *
     * @param selectedName  oldName
     * @param updateName  newName
     * @param selectedType 对应类型
     * @return int 影响的行数
     */
     int update(String selectedName,String updateName, String selectedType);

    /**
     * 设置笔记分组
     * @param selectedGroup 选中的笔记分组
     * @param locatedFolder 目标知识库
     * @return
     */
     int setGroup(String selectedGroup,String locatedFolder);

    /**
     * 选择分组时判断是否为分组
     * @param selectedClassName 选中的对应类
     * @return
     */
     boolean isGroup(String selectedClassName);

    /**
     * 根据xxx获取id
     * @param obj xxx
     * @return String id
     */
    String getId(Object obj);

    /**
     * 根据xxx获取其下所有id
     * @param obj xxx
     * @return LinkedList id链表
     */
    LinkedList getIds(Object obj);
}
