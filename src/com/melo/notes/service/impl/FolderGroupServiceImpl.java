package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.FolderDaoImpl;
import com.melo.notes.dao.impl.GroupDaoImpl;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.Group;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.FolderGroupService;
import com.melo.notes.util.BeanFactory;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 设置笔记知识库分组业务逻辑实现类
 * @date 2021-4-1 16:30
 */
public class FolderGroupServiceImpl implements FolderGroupService {
    /**
     * 相关操作类对象
     */
    FolderDaoImpl folderDao = (FolderDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.FolderDao);
    GroupDaoImpl groupDao = (GroupDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.GroupDao);
    /**
     * 各对象所在层级
     */
    private final int FOLDERTREEPATHCOUNT=2;
    private final int GROUPTREEPATHCOUNT=3;
    /**
     * 相应对象名称
     */
    private final String FOLDER="Folder";
    private final String GROUP="Group";

    /**
     * 根据节点数判断是什么类
     * @param TreePathCount
     * @return String 对象名称
     * @notice 没有选中则返回null
     */
    @Override
    public String judgeType(int TreePathCount) {
        switch (TreePathCount){
            case FOLDERTREEPATHCOUNT: return FOLDER;
            case GROUPTREEPATHCOUNT: return GROUP;
            default:return " ";
        }
    }

    /**
     * 根据用户Id获取知识库名称
     * @param user 用户
     * @return ResultSet 结果集
     */
    @Override
    public HashMap<Object, Object> showFolderName(User user) {
        return folderDao.showFolderName(user);
    }

    /**
     * 根据知识库列出分组界面
     * @param folderId 知识库Id
     * @return ResultSet 结果集
     */
    @Override
    public LinkedList<Object> showNoteGroup(String folderId) {
        return groupDao.showNoteGroup(folderId);
    }

    /**
     * 根据传入类名删除对应类对象
     *
     * @param selectedName      对象名称
     * @param selectedType 对应类型
     * @return int 影响的行数
     */
    @Override
    public int delete(String selectedName, String selectedType) {
        /**
         * 没有正常选择要删除的节点
         */
        if(selectedType.isEmpty()||selectedName.isEmpty()){
            return 0;
        }
        if(selectedType.equals(FOLDER)){
            Folder folder = new Folder();
            folder.setFolderName(selectedName);
            String folderId =getId(folder);
            return folderDao.deleteFolder(folderId);
        }
        if(selectedType.equals(GROUP)){
            Group group = new Group();
            group.setGroupName(selectedName);
            String groupId=getId(group);
            return groupDao.deleteGroup(groupId);
        }
        return 0;
    }


    /**
     * 根据传入类名删除对应类对象
     *
     * @param selectedName  oldName
     * @param updateName  newName
     * @param selectedType 对应类型
     * @return int 影响的行数
     */
    @Override
    public int update(String selectedName,String updateName, String selectedType) {
        /**
         * 没有正常选择要修改的节点
         */
        if(selectedType.isEmpty()||selectedName.isEmpty()){
            return 0;
        }
        if(selectedType.equals(FOLDER)){
            Folder folder = new Folder();
            folder.setFolderName(selectedName);
            String folderId =getId(folder);
            folder.setId(folderId);
            folder.setFolderName(updateName);
            return folderDao.updateFolderName(folder);
        }
        if(selectedType.equals(GROUP)){
            Group group = new Group();
            group.setGroupName(selectedName);
            String groupId=getId(group);
            group.setId(groupId);
            group.setGroupName(updateName);
            return groupDao.updateGroupName(group);
        }
        return 0;
    }

    /**
     * 选择分组时判断是否为分组
     * @param selectedClassName 选中的对应类
     * @return
     */
    @Override
    public boolean isGroup(String selectedClassName) {
        if(selectedClassName.equals(GROUP)){
            return true;
        }
        return false;
    }



    /**
     * 根据xxx获取id
     * @param obj xxx
     * @return String id
     */
    @Override
    public String getId(Object obj ) {
        return groupDao.getId(obj);
    }

}

