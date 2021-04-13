package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.BaseDaoImpl;
import com.melo.notes.dao.impl.FolderDaoImpl;
import com.melo.notes.dao.impl.GroupDaoImpl;
import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.Group;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.FolderGroupService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.FolderView;
import com.melo.notes.view.LoginView;
import com.melo.notes.view.UpdateNoteView;

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
    NoteDaoImpl noteDao=(NoteDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.NoteDao);
    /**
     * 各对象所在层级
     */
    private final int FOLDER_TYPE =2;
    private final int GROUP_TYPE =3;
    private final int NOTE_TYPE =4;
    /**
     * 相应对象名称
     */
    private final String FOLDER="知识库";
    private final String GROUP="笔记分组";
    private final String NOTE="笔记";
    private final String DEFAULT=LoginView.USER.getUserName();
    private final String PUBLIC="公开";

    /**
     * 根据节点数判断是什么类
     * @param treePathCount 节点数
     * @return String 对象名称
     * @notice 没有选中则返回null
     */
    @Override
    public String judgeType(int treePathCount) {
        switch (treePathCount){
            case FOLDER_TYPE: return FOLDER;
            case GROUP_TYPE: return GROUP;
            case NOTE_TYPE: return NOTE;
            default:return " ";
        }
    }

    /**
     * 根据登录的用户Id获取知识库名称
     *
     * @return HashMap 知识库id-知识库名称
     */
    @Override
    public HashMap showFolderName() {
        User user = new User();
        user.setId(LoginView.USER.getId());
        return folderDao.showFolderName(user);
    }

    /**
     * 根据知识库列出分组界面
     * @param folderId 知识库Id
     * @return HashMap 笔记分组id-笔记分组名称
     */
    @Override
    public HashMap<Object, Object> showNoteGroup(String folderId) {
        Folder folder = new Folder();
        folder.setId(folderId);
        return groupDao.showNoteGroup(folder);
    }

    /**
     * 根据传入类名删除对应类对象
     *
     * @param selectedName  选中名称
     * @param selectedType 对应类型
     * @return int 影响的行数
     */
    @Override
    public int delete(String selectedName, String selectedType) {
        /*
          没有正常选择要删除的节点或选中的为默认
         */
        if(selectedType.isEmpty()||selectedName.isEmpty()||DEFAULT.equals(selectedName)){
            return 0;
        }
        //删除知识库
        if(selectedType.equals(FOLDER)){
            Folder folder = new Folder();
            folder.setFolderName(selectedName);
            String folderId =getId(folder);
            folderDao.deleteFolder(folderId);
            /*
            将其下的笔记分组移至默认知识库
             */
            Group group = new Group();
            group.setLocatedFolder(folderId);
            //获取其下所有id
            LinkedList groupIds = getIds(group);
            if(!groupIds.isEmpty()) {
                //根据id遍历删除
                for (Object groupId : groupIds) {
                    Group temp = new Group();
                    temp.setId(groupId.toString());
                    groupDao.delete(temp);
                }
            }
        }
        if(selectedType.equals(GROUP)){
            Group tempGroup = new Group();
            tempGroup.setGroupName(selectedName);
            String groupId=getId(tempGroup);
            groupDao.deleteGroup(groupId);
            /*
            将其下的笔记移至默认分组
             */
            Note note = new Note();
            note.setLocatedGroup(groupId);
            //获取其下所有id
            LinkedList noteIds = getIds(note);
            if(!noteIds.isEmpty()) {
                //根据id遍历删除
                for (Object noteId : noteIds) {
                    Note temp = new Note();
                    temp.setId(noteId.toString());
                    noteDao.delete(temp);
                }
            }
        }
        if(selectedType.equals(NOTE)){
            Note tempNote = new Note();
            tempNote.setTitle(selectedName);
            String noteId=getId(tempNote);
            noteDao.deleteNote(noteId);
        }
        return 1;
    }

    /**
     * 为新用户初始化知识库和笔记分组
     * @notice 会生成默认知识库和默认笔记分组,且不允许修改和删除
     */
    @Override
    public void initFolderGroup(){
        //该用户没有知识库
        if(showFolderName().isEmpty()) {
            addFolder(DEFAULT, PUBLIC, LoginView.USER.getId());
            addGroup(DEFAULT, DEFAULT);
        }
    }

    /**
     * 新增知识库
     * @param name 知识库名称
     * @param access 知识库权限
     * @param authorId 作者id
     * @return boolean 是否增加成功
     */
    @Override
    public boolean addFolder(String name, String access,String authorId){
            Folder folder = new Folder();
            folder.setFolderName(name);
            folder.setAccess(access);
            folder.setAuthorId(authorId);
            return folderDao.addFolder(folder);
    }

    /**
     * 新增笔记分组
     * @param groupName 笔记分组名称
     * @param locatedFolder 所在知识库
     * @return boolean 操作是否成功
     */
    @Override
    public boolean addGroup(String groupName, String locatedFolder){
        Group group = new Group();
        group.setGroupName(groupName);
        group.setAuthorId(LoginView.USER.getId());
        Folder folder = new Folder();
        //根据知识库名称反向获取知识库id
        folder.setFolderName(locatedFolder);
        group.setLocatedFolder(getId(folder));
        return groupDao.insert(group)==1;
    }

    /**
     * 根据前台传入参数更新相应对象
     *
     * @param selectedName  oldName
     * @param updateName  newName
     * @param selectedType 对应类型
     * @return int 影响的行数
     */
    @Override
    public int update(String selectedName, String updateName, String selectedType) {
        /*
          没有正常选择要修改的节点
         */
        if(selectedType.isEmpty()||selectedName.isEmpty()||DEFAULT.equals(selectedName)){
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
            String groupId = getId(group);
            group.setId(groupId);
                group.setGroupName(updateName);
                return groupDao.updateGroup(group);
            }
        if(selectedType.equals(NOTE)){
            new UpdateNoteView(LoginView.USER);
            return 0;
        }
        return -1;
    }

    /**
     * 设置笔记分组
     * @param selectedGroup 选中的笔记分组
     * @param locatedFolder 目标知识库
     * @return int 影响的行数
     */
    @Override
    public int setGroup(String selectedGroup,String locatedFolder){
        if(isGroup(FolderView.selectedType)) {
            Folder folder = new Folder();
            folder.setFolderName(locatedFolder);
            Group group = new Group();
            group.setGroupName(selectedGroup);
            String groupId = getId(group);
            group.setId(groupId);
            group.setLocatedFolder(getId(folder));
            group.setGroupName(null);
            return groupDao.update(group);
        }
        return 0;
    }


    /**
     * 选择分组时判断是否为分组
     * @param selectedClassName 选中的对应类
     * @return boolean 是否为笔记分组
     */
    @Override
    public boolean isGroup(String selectedClassName) {
        return selectedClassName.equals(GROUP);
    }

    public boolean isNote(String selectedClassName) {
        return selectedClassName.equals(NOTE);
    }

    /**
     * 根据xxx获取单一id
     * @param obj xxx
     * @return String id
     */
    @Override
    public String getId(Object obj ) {
        return groupDao.getId(obj).getFirst().toString();
    }

    /**
     * 根据xxx获取其下所有id
     * @param obj xxx
     * @return LinkedList id链表
     */
    @Override
    public LinkedList getIds(Object obj){
        return  groupDao.getId(obj);
    }

}

