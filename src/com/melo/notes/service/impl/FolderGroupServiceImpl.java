package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.BaseDaoImpl;
import com.melo.notes.dao.impl.FolderDaoImpl;
import com.melo.notes.dao.impl.GroupDaoImpl;
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

/**
 * @author Jun
 * @program Note
 * @description ���ñʼ�֪ʶ�����ҵ���߼�ʵ����
 * @date 2021-4-1 16:30
 */
public class FolderGroupServiceImpl implements FolderGroupService {
    /**
     * ��ز��������
     */
    FolderDaoImpl folderDao = (FolderDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.FolderDao);
    GroupDaoImpl groupDao = (GroupDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.GroupDao);
    /**
     * ���������ڲ㼶
     */
    private final int FOLDERTYPE =2;
    private final int GROUPTYPE =3;
    private final int NOTETYPE =4;
    /**
     * ��Ӧ��������
     */
    private final String FOLDER="֪ʶ��";
    private final String GROUP="�ʼǷ���";
    private final String NOTE="�ʼ�";

    /**
     * ���ݽڵ����ж���ʲô��
     * @param TreePathCount
     * @return String ��������
     * @notice û��ѡ���򷵻�null
     */
    @Override
    public String judgeType(int TreePathCount) {
        switch (TreePathCount){
            case FOLDERTYPE: return FOLDER;
            case GROUPTYPE: return GROUP;
            case NOTETYPE: return NOTE;
            default:return " ";
        }
    }

    /**
     * ���ݵ�¼���û�Id��ȡ֪ʶ������
     * @return HashMap ֪ʶ��id-֪ʶ������
     */
    @Override
    public HashMap<Object, Object> showFolderName() {
        User user = new User();
        user.setId(LoginView.USER.getId());
        return folderDao.showFolderName(user);
    }

    /**
     * ����֪ʶ���г��������
     * @param folderId ֪ʶ��Id
     * @return
     */
    @Override
    public HashMap<Object, Object> showNoteGroup(String folderId) {
        return groupDao.showNoteGroup(folderId);
    }

    /**
     * ���ݴ�������ɾ����Ӧ�����
     *
     * @param selectedName      ��������
     * @param selectedType ��Ӧ����
     * @return int Ӱ�������
     */
    @Override
    public int delete(String selectedName, String selectedType) {
        /**
         * û������ѡ��Ҫɾ���Ľڵ�
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
     * ����֪ʶ��
     * @param name
     * @param access
     * @return
     */
    @Override
    public boolean addFolder(String name, String access){
            Folder folder = new Folder();
            folder.setFolderName(name);
            folder.setAccess(access);
            folder.setAuthorId(LoginView.USER.getId());
            return folderDao.addFolder(folder);
    }

    /**
     * �����ʼǷ���
     * @param name
     * @param locatedFolder
     * @return
     */
    @Override
    public boolean addGroup(String name, String locatedFolder){
        Group group = new Group();
        group.setGroupName(name);
        group.setAuthorId(LoginView.USER.getId());
        Folder folder = new Folder();
        //����֪ʶ�����Ʒ����ȡ֪ʶ��id
        folder.setFolderName(locatedFolder);
        group.setLocatedFolder(getId(folder));
        return groupDao.insert(group)==1;
    }

    /**
     * ����ǰ̨�������������Ӧ��������
     *
     * @param selectedName  oldName
     * @param updateName  newName
     * @param selectedType ��Ӧ����
     * @return int Ӱ�������
     */
    @Override
    public int update(String selectedName,String updateName, String selectedType) {
        /**
         * û������ѡ��Ҫ�޸ĵĽڵ�
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
            String groupId = getId(group);
            group.setId(groupId);
                group.setGroupName(updateName);
                return groupDao.updateGroupName(group);
            }
        if(selectedType.equals(NOTE)){
            new UpdateNoteView(LoginView.USER);
            return 1;
        }
        return 0;
    }

    /**
     * ���ñʼǷ���
     * @param selectedGroup ѡ�еıʼǷ���
     * @param locatedFolder Ŀ��֪ʶ��
     * @return
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

    public int setNote(String selectedNote,String locatedGroup){
        if(isNote(FolderView.selectedType)){
            Group group = new Group();
            group.setGroupName(locatedGroup);
            Note note = new Note();
            note.setTitle(selectedNote);
            String id = getId(note);
            note.setId(id);
            note.setLocatedGroup(getId(group));
            note.setTitle(null);
            return new BaseDaoImpl().update(note);
        }
        return 0;
    }


    /**
     * ѡ�����ʱ�ж��Ƿ�Ϊ����
     * @param selectedClassName ѡ�еĶ�Ӧ��
     * @return
     */
    @Override
    public boolean isGroup(String selectedClassName) {
        if(selectedClassName.equals(GROUP)){
            return true;
        }
        return false;
    }

    public boolean isNote(String selectedClassName) {
        if(selectedClassName.equals(NOTE)){
            return true;
        }
        return false;
    }



    /**
     * ����xxx��ȡid
     * @param obj xxx
     * @return String id
     */
    @Override
    public String getId(Object obj ) {
        return groupDao.getId(obj);
    }

}

