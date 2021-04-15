package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.FolderDaoImpl;
import com.melo.notes.dao.impl.GroupDaoImpl;
import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Folder;
import com.melo.notes.entity.Group;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.exception.DaoException;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.constant.TypeName;
import com.melo.notes.service.inter.FolderGroupService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.FolderView;
import com.melo.notes.view.LoginView;

import javax.swing.*;
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
     * ���ݵ�¼���û�Id��ȡ֪ʶ��id������
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
        if(super.notNull(folderId)) {
            Folder folder = new Folder();
            folder.setId(folderId);
            return groupDao.showNoteGroup(folder);
        }
        return null;
    }

    /**
     * ���ݴ�������ɾ����Ӧ�����
     *
     * @param selectedName      ��������
     * @param selectedType ��Ӧ����
     * @param selectedId ��Ӧid
     * @return int Ӱ�������
     */
    @Override
    public int delete(String selectedName, String selectedType,String selectedId) {
        /*
          ��ֹɾ��Ĭ��֪ʶ��ʼǷ���
         */
        if (TypeName.DEFAULT.getMessage().equals(selectedName)) {
            return 0;
        }
        if(super.notNull(selectedName,selectedType,selectedId)) {
            if (JOptionPane.showConfirmDialog(null,
                    "ȷ��ɾ���밴��", "ȷ��ɾ��", JOptionPane.YES_NO_OPTION) == 0) {
            /*
            ɾ��֪ʶ��
             */
                if (selectedType.equals(TypeName.FOLDER.getMessage())) {
                    //����idɾ��֪ʶ��
                    String folderId = selectedId;
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
                            groupDao.deleteGroup(groupId.toString());
                        }
                    }
                    //���ܸ�֪ʶ����û�бʼǷ���,����������������ֵ
                    return folderDao.deleteFolder(folderId);
                }
        /*
        ɾ���ʼǷ���
         */
                if (selectedType.equals(TypeName.GROUP.getMessage())) {
                    Group group = new Group();
                    String groupId = FolderView.selectedId;
                    group.setId(groupId);
            /*
            ���ñʼǷ������µıʼ�����Ĭ�ϱʼǷ���
             */
                    Note note = new Note();
                    note.setLocatedGroup(groupId);
                    //��ȡ�ñʼǷ����������бʼ�id
                    LinkedList noteIds = getIds(note);
                    if (!noteIds.isEmpty()) {
                        //���ݱʼ�id����ɾ��
                        for (Object noteId : noteIds) {
                            noteDao.deleteNote(noteId.toString());
                        }
                    }
                    //���ܸñʼǷ�����û�бʼ�,����������������ֵ
                    return groupDao.deleteGroup(groupId);
                }
        /*
        ɾ���ʼ�
         */
                if (selectedType.equals(TypeName.NOTE.getMessage())) {
                    //������ʱ�����ȡid
                    return noteDao.deleteNote(selectedId);
                }
            }
            return 0;
        }
        return 0;
    }

    /**
     * Ϊ���û���ʼ��֪ʶ��ͱʼǷ���
     * @notice ������Ĭ��֪ʶ���Ĭ�ϱʼǷ���,�Ҳ������޸ĺ�ɾ��
     */
    @Override
    public void initFolderGroup(){
        //������û�û��֪ʶ��,����Ĭ��֪ʶ��ͱʼǷ���
        if(showFolderName().isEmpty()) {
            addFolder(TypeName.DEFAULT.getMessage(), TypeName.PUBLIC.getMessage(), LoginView.USER.getId());
            addGroup(TypeName.DEFAULT.getMessage(), groupDao.getMaxId(new Folder()));
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
            try {
                Folder folder = new Folder();
                folder.setFolderName(name);
                folder.setAccess(access);
                folder.setAuthorId(authorId);
                return folderDao.addFolder(folder);
            }catch (Exception e) {
                e.printStackTrace();
                throw new DaoException("��֪ʶ�������Ѵ���");
            }
        }
        return false;
    }

    /**
     * �����ʼǷ���
     * @param groupName �ʼǷ�������
     * @param folderId ����֪ʶ��id
     * @return boolean �����Ƿ�ɹ�
     */
    @Override
    public boolean addGroup(String groupName, String folderId){
        if(super.notNull(groupName,folderId)) {
            //��װ���������ʼǷ���
            Group group = new Group();
            group.setLocatedFolder(folderId);
            group.setGroupName(groupName);
            group.setAuthorId(LoginView.USER.getId());
            return groupDao.insert(group) == 1;
        }
        return false;
    }

    /**
     * �����ʼ�
     * @param title ����
     * @param text �ı�����
     * @param access Ȩ��
     * @param groupId ���ڱʼǷ���id
     * @return boolean �Ƿ����ӳɹ�
     */
    @Override
    public boolean addNote(String title, String text, String access, String groupId){
        if(super.notNull(title,text,access,groupId)) {
            Note note = new Note(title, LoginView.USER.getId(), text, access, 0, groupId);
            System.out.println(LoginView.USER.getId()+"��ǰ��"+LoginView.USER.getUserName());
            return noteDao.addNote(note);
        }
        return false;
    }

    /**
     * ����ǰ̨�������������Ӧ����
     *
     * @param selectedName  oldName
     * @param selectedType ��Ӧ����
     * @return int Ӱ�������
     */
    @Override
    public int update(String selectedName, String selectedType) {
        /*
          ��ֹ�޸�Ĭ��֪ʶ��ʼǷ���
         */
        if ( TypeName.DEFAULT.getMessage().equals(selectedName)) {
            return 0;
        }
        String updateName = JOptionPane.showInputDialog("�������޸�����");
        if(super.notNull(updateName,selectedName,selectedType)) {
            if (selectedType.equals(TypeName.FOLDER.getMessage())) {
                //��װ����ִ�и���
                Folder folder = new Folder();
                folder.setId(FolderView.selectedId);
                folder.setFolderName(updateName);
                return folderDao.updateFolderName(folder);
            }
            if (selectedType.equals(TypeName.GROUP.getMessage())) {
                //��װ����ִ�и���
                Group group = new Group();
                group.setId(FolderView.selectedId);
                group.setGroupName(updateName);
                return groupDao.updateGroup(group);
            }
        }
        return 0;
    }

    /**
     * ���ñʼǷ���
     * @param groupName ѡ�еıʼǷ�������
     * @param locatedFolder Ŀ��֪ʶ������
     * @param groupId ѡ�еıʼ�id
     * @return int Ӱ�������
     */
    @Override
    public int setGroup(String groupName,String locatedFolder,String groupId){
        if(super.notNull(groupName,locatedFolder,groupId)) {
            if (isGroup(FolderView.selectedType)) {
                //�ضϻ�ȡ֪ʶ��id
                int location = locatedFolder.indexOf("-");
                String folderId = locatedFolder.substring(0, location);
                //��װ�ʼǶ���,���±ʼ�
                Group group = new Group();
                group.setId(groupId);
                group.setLocatedFolder(folderId);
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
        if(super.notNull(obj.toString())) {
            return groupDao.getId(obj).getFirst().toString();
        }
        return null;
    }

    /**
     * ����xxx��ȡ��������id
     * @param obj xxx
     * @return LinkedList id����
     */
    @Override
    public LinkedList getIds(Object obj){
        if(super.notNull(obj.toString())) {
            return groupDao.getId(obj);
        }
        return null;
    }

}

