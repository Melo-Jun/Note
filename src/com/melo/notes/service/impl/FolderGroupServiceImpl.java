package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.FolderDaoImpl;
import com.melo.notes.dao.impl.GroupDaoImpl;
import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.Group;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.service.constant.TypeName;
import com.melo.notes.service.inter.FolderGroupService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.FolderView;
import com.melo.notes.view.LoginView;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description ���ñʼ�֪ʶ�����ҵ���߼�ʵ����
 * @date 2021-4-1 16:30
 */
public class FolderGroupServiceImpl extends BaseServiceImpl implements FolderGroupService {

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
    private final String DEFAULT=LoginView.USER.getUserName();

    /**
     * ���ݽڵ����ж���ʲô��
     * @param treePathCount �ڵ���
     * @return String ��������
     * @notice û��ѡ���򷵻�null
     */
    @Override
    public String judgeType(int treePathCount) {
        switch (treePathCount){
            case FOLDER_TYPE: return TypeName.FOLDER.getMessage();
            case GROUP_TYPE: return TypeName.GROUP.getMessage();
            case NOTE_TYPE: return TypeName.NOTE.getMessage();
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
     * @return HashMap �ʼǷ���id-�ʼǷ�������
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
     * @param selectedName  ѡ������
     * @param selectedType ��Ӧ����
     * @notice ɾ��֪ʶ�����������µ�����Ĭ�ϱʼǷ���
     * @return int Ӱ�������
     */
    @Override
    public int delete(String selectedName, String selectedType) {
        if(super.notNull(selectedName,selectedType)) {
        /*
          ��ֹɾ��Ĭ��֪ʶ��ʼǷ���
         */
            if (DEFAULT.equals(selectedName)) {
                return 0;
            }
            //ɾ��֪ʶ��
            if (selectedType.equals(TypeName.FOLDER.getMessage())) {
                //������ʱ�����ȡid
                Folder folder = new Folder();
                folder.setFolderName(selectedName);
                //����idɾ��֪ʶ��
                String folderId = getId(folder);
                folderDao.deleteFolder(folderId);
            /*
            ����֪ʶ�����µıʼǷ�������Ĭ��֪ʶ��
             */
                Group group = new Group();
                group.setLocatedFolder(folderId);
                //��ȡ��֪ʶ���������бʼǷ���id
                LinkedList groupIds = getIds(group);
                if (!groupIds.isEmpty()) {
                    //���ݱʼǷ���id����ɾ��
                    for (Object groupId : groupIds) {
                        Group temp = new Group();
                        temp.setId(groupId.toString());
                        groupDao.delete(temp);
                    }
                }
            }
            return 0;
        }
        //ɾ���ʼǷ���
        if(selectedType.equals(TypeName.GROUP.getMessage())){
            //������ʱ�����ȡid
            Group tempGroup = new Group();
            tempGroup.setGroupName(selectedName);
            String groupId=getId(tempGroup);
            groupDao.deleteGroup(groupId);
            /*
            ���ñʼǷ������µıʼ�����Ĭ�ϱʼǷ���
             */
            Note note = new Note();
            note.setLocatedGroup(groupId);
            //��ȡ�ñʼǷ����������бʼ�id
            LinkedList noteIds = getIds(note);
            if(!noteIds.isEmpty()) {
                //���ݱʼ�id����ɾ��
                for (Object noteId : noteIds) {
                    Note temp = new Note();
                    temp.setId(noteId.toString());
                    noteDao.delete(temp);
                }
            }
        }
        //ɾ���ʼ�
        if(selectedType.equals(TypeName.NOTE.getMessage())){
            //������ʱ�����ȡid
            Note tempNote = new Note();
            tempNote.setTitle(selectedName);
            String noteId=getId(tempNote);
            noteDao.deleteNote(noteId);
        }
        return 1;
    }

    /**
     * Ϊ���û���ʼ��֪ʶ��ͱʼǷ���
     * @notice ������Ĭ��֪ʶ���Ĭ�ϱʼǷ���,�Ҳ������޸ĺ�ɾ��
     */
    @Override
    public void initFolderGroup(){
        //������û�û��֪ʶ��
        if(showFolderName().isEmpty()) {
            addFolder(DEFAULT, TypeName.PUBLIC.getMessage(), LoginView.USER.getId());
            addGroup(DEFAULT, DEFAULT);
        }
    }

    /**
     * ����֪ʶ��
     * @param name ֪ʶ������
     * @param access ֪ʶ��Ȩ��
     * @param authorId ����id
     * @return boolean �Ƿ����ӳɹ�
     */
    @Override
    public boolean addFolder(String name, String access,String authorId){
        if(super.notNull(name,access,authorId)) {
            Folder folder = new Folder();
            folder.setFolderName(name);
            folder.setAccess(access);
            folder.setAuthorId(authorId);
            return folderDao.addFolder(folder);
        }
        return false;
    }

    /**
     * �����ʼǷ���
     * @param groupName �ʼǷ�������
     * @param locatedFolder ����֪ʶ������
     * @return boolean �����Ƿ�ɹ�
     */
    @Override
    public boolean addGroup(String groupName, String locatedFolder){
        if(super.notNull(groupName,locatedFolder)) {
            Group group = new Group();
            //����֪ʶ�����Ʒ����ȡ֪ʶ��id
            Folder folder = new Folder();
            folder.setFolderName(locatedFolder);
            group.setLocatedFolder(getId(folder));

            group.setGroupName(groupName);
            group.setAuthorId(LoginView.USER.getId());
            return groupDao.insert(group) == 1;
        }
        return false;
    }

    /**
     * ����ǰ̨�������������Ӧ����
     *
     * @param selectedName  oldName
     * @param updateName  newName
     * @param selectedType ��Ӧ����
     * @return int Ӱ�������
     */
    @Override
    public int update(String selectedName, String updateName, String selectedType) {
        if(super.notNull(updateName,selectedName,selectedType)) {
        /*
          ��ֹ�޸�Ĭ��֪ʶ��ʼǷ���
         */
            if ( DEFAULT.equals(selectedName)) {
                return 0;
            }
            if (selectedType.equals(TypeName.FOLDER.getMessage())) {
                //�ȸ���oldName��ȡid
                Folder folder = new Folder();
                folder.setFolderName(selectedName);
                String folderId = getId(folder);
                //newName����oldName
                folder.setId(folderId);
                folder.setFolderName(updateName);
                return folderDao.updateFolderName(folder);
            }
            if (selectedType.equals(TypeName.GROUP.getMessage())) {
                //�ȸ���oldName��ȡid
                Group group = new Group();
                group.setGroupName(selectedName);
                String groupId = getId(group);
                //newName����oldName
                group.setId(groupId);
                group.setGroupName(updateName);
                return groupDao.updateGroup(group);
            }
        }
        return 0;
    }

    /**
     * ���ñʼǷ���
     * @param selectedGroup ѡ�еıʼǷ���
     * @param locatedFolder Ŀ��֪ʶ������
     * @return int Ӱ�������
     */
    @Override
    public int setGroup(String selectedGroup,String locatedFolder){
        if(super.notNull(selectedGroup,locatedFolder)) {
            if (isGroup(FolderView.selectedType)) {
                //����֪ʶ�����Ʒ����ȡid
                Folder folder = new Folder();
                folder.setFolderName(locatedFolder);

                //���ݱʼǷ������Ʒ����ȡid
                Group group = new Group();
                group.setGroupName(selectedGroup);
                String groupId = getId(group);

                group.setId(groupId);
                group.setLocatedFolder(getId(folder));
                group.setGroupName(null);
                return groupDao.update(group);
            }
        }
        return 0;
    }


    /**
     * ѡ�����ʱ�ж��Ƿ�Ϊ����
     * @param selectedClassName ѡ�еĶ�Ӧ��
     * @return boolean �Ƿ�Ϊ�ʼǷ���
     */
    @Override
    public boolean isGroup(String selectedClassName) {
        if(super.notNull(selectedClassName)) {
            return selectedClassName.equals(TypeName.GROUP.getMessage());
        }
        return false;
    }

    /**
     * ����xxx��ȡ��һid
     * @description ִ��update��delete��������Ҫ����id
     * @param obj xxx
     * @return String id
     */
    @Override
    public String getId(Object obj ) {
        return groupDao.getId(obj).getFirst().toString();
    }

    /**
     * ����xxx��ȡ��������id
     * @param obj xxx
     * @return LinkedList id����
     */
    @Override
    public LinkedList getIds(Object obj){
        return  groupDao.getId(obj);
    }

}

