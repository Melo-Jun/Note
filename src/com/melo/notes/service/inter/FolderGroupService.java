package com.melo.notes.service.inter;


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
     * 为新用户初始化知识库和笔记分组
     * @notice 会生成默认知识库和默认笔记分组,且不允许修改和删除
     */
     void initFolderGroup();

    /**
     * 新增知识库
     * @param name 知识库名称
     * @param access 知识库权限
     * @param authorId 作者id
     * @return boolean 是否增加成功
     */
     boolean addFolder(String name, String access,String authorId);

    /**
     * 新增笔记分组
     * @param groupName 笔记分组名称
     * @param locatedFolder 所在知识库
     * @return boolean 操作是否成功
     */
     boolean addGroup(String groupName,String locatedFolder);

    /**
     * 根据前台传入参数更新相应对象
     *
     * @param selectedName  oldName
     * @param updateName  newName
     * @param selectedType 对应类型
     * @return int 影响的行数
     */
     int update(String selectedName, String updateName, String selectedType);

    /**
     * 设置笔记分组
     * @param selectedGroup 选中的笔记分组
     * @param locatedFolder 目标知识库
     * @return int 影响的行数
     */
     int setGroup(String selectedGroup,String locatedFolder);

    /**
     * 选择分组时判断是否为分组
     * @param selectedClassName 选中的对应类
     * @return boolean 是否为笔记分组
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
