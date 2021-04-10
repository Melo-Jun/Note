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
    NoteDaoImpl noteDao=(NoteDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.NoteDao);
    /**
     * ���������ڲ㼶
     */
    private final int FOLDER_TYPE =2;
    private final int GROUP_TYPE =3;
    private final int NOTE_TYPE =4;
    /**
     * ��Ӧ��������
     */
    private final String FOLDER="֪ʶ��";
    private final String GROUP="�ʼǷ���";
    private final String NOTE="�ʼ�";
    private final String DEFAULT=LoginView.USER.getUserName();
    private final String PUBLIC="����";

    /**
     * ���ݽڵ����ж���ʲô��
     * @param treePathCount �ڵ���
     * @return String ��������
     * @notice û��ѡ���򷵻�null
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
     * ���ݵ�¼���û�Id��ȡ֪ʶ������
     *
     * @return HashMap ֪ʶ��id-֪ʶ������
     */
    @Override
    public HashMap showFolderName() {
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
        Folder folder = new Folder();
        folder.setId(folderId);
        return groupDao.showNoteGroup(folder);
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
        if(selectedType.isEmpty()||selectedName.isEmpty()||DEFAULT.equals(selectedName)){
            return 0;
        }
        if(selectedType.equals(FOLDER)){
            //ɾ��֪ʶ��
            Folder folder = new Folder();
            folder.setFolderName(selectedName);
            String folderId =getId(folder);
            folderDao.deleteFolder(folderId);
            //ɾ����Ӧ�ʼǷ���
            /*Group group = new Group();
            group.setLocatedFolder(folderId);
            groupDao.deleteGroup(group);*/
            //ɾ����Ӧ�ʼ�
            /*Note note = new Note();
            String groupId=getId(group);
            /*note.setLocatedGroup(groupId);
            noteDao.deleteNote()*/
        }
        if(selectedType.equals(GROUP)){
            Group tempGroup = new Group();
            tempGroup.setGroupName(selectedName);
            String groupId=getId(tempGroup);
            Group group = new Group();
            group.setId(groupId);
            return groupDao.deleteGroup(group);
        }
        if(selectedType.equals(NOTE)){
            Note tempNote = new Note();
            tempNote.setTitle(selectedName);
            String noteId=getId(tempNote);
            Note note = new Note();
            note.setId(noteId);
            return noteDao.deleteNote(note);
        }
        return 0;
    }

    public void initFolderGroup(){
        //���û�û��֪ʶ��
        if(showFolderName().isEmpty()) {
            addFolder(DEFAULT, PUBLIC, LoginView.USER.getId());
            System.out.println(LoginView.USER.getId());
            addGroup(DEFAULT, DEFAULT);
        }
    }

    /**
     * ����֪ʶ��
     * @param name
     * @param access
     * @param authorId
     * @return
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
     * �����ʼǷ���
     * @param groupName �ʼǷ�������
     * @param locatedFolder ����֪ʶ��
     * @return boolean �����Ƿ�ɹ�
     */
    @Override
    public boolean addGroup(String groupName, String locatedFolder){
        Group group = new Group();
        group.setGroupName(groupName);
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

