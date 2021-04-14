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
     * @param selectedId 对应id
     * @return int 影响的行数
     */
     int delete(String selectedName,String selectedType,String selectedId);

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
     * @param folderId 所在知识库id
     * @return boolean 操作是否成功
     */
     boolean addGroup(String groupName,String folderId);

    /**
     * 新增笔记
     * @param title 标题
     * @param text 文本内容
     * @param access 权限
     * @param groupId 所在笔记分组id
     * @return boolean 是否增加成功
     */
     boolean addNote(String title,String text,String access,String groupId);

    /**
     * 根据前台传入参数更新相应对象
     *
     * @param selectedName  oldName
     * @param selectedType 对应类型
     * @return int 影响的行数
     */
     int update(String selectedName,  String selectedType);

    /**
     * 设置笔记分组
     * @param groupName 选中的笔记分组名称
     * @param locatedFolder 目标知识库名称
     * @param groupId 选中的笔记id
     * @return int 影响的行数
     */
    int setGroup(String groupName,String locatedFolder,String groupId);

    /**
     * 选择分组时判断是否为分组
     * @param selectedClassName 选中的对应类
     * @return boolean 是否为笔记分组
     */
     boolean isGroup(String selectedClassName);

    /**
     * 根据xxx获取单一id
     * @description 执行update或delete操作都需要根据id
     * @param obj xxx
     * @return String id
     */
    String getId(Object obj);

    /**
     * 根据xxx获取所有id
     * @param obj xxx
     * @description 删除知识库时需要获取其下所有的笔记分组id
     * @return LinkedList id链表
     */
    LinkedList getIds(Object obj);
}
