

package com.melo.notes.view;

import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.service.impl.NoteServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Jun
 */
public class FolderView extends JFrame {

    /**
     * �û�
     */
    User user=LoginView.USER;

    /**
     * ѡ�е�����
     */
    public static String selectedName ="";
    /**
     * ѡ�е�����
     */
    public static String selectedType ="";
    private final String NOTE="�ʼ�";
    private final String GROUP="�ʼǷ���";

    JFrame jf = new JFrame("���ñʼǷ���");
    private Listener l = new Listener();
    private JButton delete = new JButton("ɾ��");
    private JButton flush = new JButton("ˢ��");
    private JButton addFolder = new JButton("����֪ʶ��");
    private JButton addGroup = new JButton("�����ʼǷ���");
    private JButton update = new JButton("�޸�����");
    private JButton setGroup = new JButton("���ñʼǷ���");
    private JButton updateNote = new JButton("���ñʼ�");
    private JButton addNote=new JButton("�����ʼ�");

    /**
     * ������ز�������
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);
    NoteServiceImpl noteService =(NoteServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.NoteService);


    public String getLocatedGroup() {
        return selectedName;
    }

    public FolderView(User user) {

            //��ʼ��֪ʶ��
            folderGroupService.initFolderGroup();

            jf.setSize(800, 600);
            jf.setLocation(330, 120);
            jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setLocation(950, 400);

            update.setBounds(100,5,100,40);
            setGroup.setBounds(200,5,120,40);
            updateNote.setBounds(300,5,120,40);
            addNote.setBounds(400,5,120,40);

            flush.setBounds(600,200,120,50);

            delete.setBounds(600,300,120,50);
            addFolder.setBounds(600,350,120,50);
            addGroup.setBounds(600,400,120,50);

        /*
          ���Ӽ�����
         */
        delete.addMouseListener(l);
        update.addMouseListener(l);
        addFolder.addMouseListener(l);
        addGroup.addMouseListener(l);
        setGroup.addMouseListener(l);
        flush.addMouseListener(l);
        updateNote.addMouseListener(l);
        addNote.addMouseListener(l);

        /*
          ���������
         */
            panel.add(flush);
            panel.add(delete);
            panel.add(update);
            panel.add(setGroup);
            panel.add(addFolder);
            panel.add(addGroup);
            panel.add(updateNote);
            panel.add(addNote);

        /*
          �������ڵ�
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("֪ʶ��");

        /*
          ����֪ʶ����������Ӧ�ʼǷ���
         */
        HashMap<Object, Object> Folder = folderGroupService.showFolderName();
                /*
                  ������ȡfolderId��folderName
                 */
                Set<Map.Entry<Object, Object>> folderSet =  Folder.entrySet();
                for(Map.Entry tempFolder:folderSet) {
                    Object folderId = tempFolder.getKey();
                    Object folderName = tempFolder.getValue();
                    /**
                     * ����folderName����֪ʶ��
                     */
                    DefaultMutableTreeNode folder = new DefaultMutableTreeNode(folderName);
                    rootNode.add(folder);
                    /**
                     * �ٸ���folderId������Ӧ�ʼǷ���
                     */
                    HashMap<Object, Object> Group = folderGroupService.showNoteGroup(folderId.toString());
                    /**
                     * ������ȡgroupId��groupName
                     */
                    Set<Map.Entry<Object, Object>> groupSet =  Group.entrySet();
                    for(Map.Entry tempGroup:groupSet) {
                        Object groupId = tempGroup.getKey();
                        Object groupName = tempGroup.getValue();
                        DefaultMutableTreeNode group = new DefaultMutableTreeNode(groupName);
                        folder.add(group);
                        /**
                         * ������groupId������Ӧ�ʼ�
                         */
                        Note note = new Note();
                        note.setLocatedGroup(groupId.toString());
                        LinkedList titles = noteService.showNoteTitle(note);
                        if(!titles.isEmpty()){
                            for(Object title:titles){
                                DefaultMutableTreeNode noteTree = new DefaultMutableTreeNode(title);
                                group.add(noteTree);
                            }
                        }
                        //�п�
                       /* if(notes[0]!=null) {
                            for (int i = 0; i < notes.length&&notes[i]!=null; i++) {
                                DefaultMutableTreeNode noteTree = new DefaultMutableTreeNode(notes[i]);
                                group.add(noteTree);
                            }
                        }*/
                    }
                }

            //ʹ�ø��ڵ㴴�������
            JTree tree = new JTree(rootNode);

            // ��������ʾ���ڵ���
            tree.setShowsRootHandles(true);

            // �������ڵ�ɱ༭
            tree.setEditable(true);


        /*
          ���ýڵ�ѡ�м�����(��ѡ�еĽڵ����ƺͶ������ͱ�������)
         */
        tree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    selectedName = e.getPath().getLastPathComponent().toString();
                    selectedType = folderGroupService.judgeType(e.getPath().getPathCount());
                    System.out.println(selectedName+selectedType);
                }
            });

            // ����������壬����������Ϊ���ڵ�չ���������Ҫ�ܴ�Ŀռ�����ʾ��������Ҫ��һ�����������������
            JScrollPane scrollPane = new JScrollPane(tree);
            scrollPane.setBounds(50,50,500,450);

            // ��ӹ�����嵽�������
            panel.add(scrollPane);

            // ���ô���������岢��ʾ
            jf.setContentPane(panel);
            jf.setVisible(true);
        }

    /**
     * Ϊ��������Ӽ�����
      */
    public class Listener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            //ˢ�²���
            if(e.getSource()==flush){
                jf.dispose();
                new FolderView(LoginView.USER);
            }
            //ɾ������
            if(e.getSource()==delete) {
                if (JOptionPane.showConfirmDialog(null,
                        "ȷ��ɾ���밴��", "ȷ��ɾ��", JOptionPane.YES_NO_OPTION)==0) {
                    if (folderGroupService.delete(selectedName, selectedType) != 0) {
                        jf.dispose();
                        new FolderView(LoginView.USER);
                    } else {
                        JOptionPane.showMessageDialog(null, "��ȷ������ȷѡ��ڵ�");
                    }
                }
            }

            /*
             **************************************************************
             *              �޸Ĳ���
             **************************************************************
             */

            //�޸�����
            if(e.getSource()==update){
                String updateName = JOptionPane.showInputDialog("�������޸�����");
                if(folderGroupService.update(selectedName,updateName,selectedType)!=0){
                    JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
                    jf.dispose();
                    new FolderView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"�޸�ʧ��");
                }
            }
            //�޸ıʼ�����
            if(e.getSource()==updateNote){
                if(selectedType.equals(NOTE)) {
                    new UpdateNoteView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"��ȷ����ѡ�е��Ǳʼ�");
                }
            }
            //�����ʼ�
            if(e.getSource()==addNote){
                if(selectedType.equals(GROUP)){
                    new AddNoteView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"��ȷ����ѡ�е��Ǵ�����ʼǵıʼǷ���");
                }
            }
            //���ñʼǷ���
            if(e.getSource()==setGroup){
                new SetGroupView().setVisible(true);
            }
            //����֪ʶ�����
            if(e.getSource()== addFolder){
               new AddFolderView().setVisible(true);
            }
            //���ӱʼǷ������
            if(e.getSource()==addGroup) {
                new AddGroupView().setVisible(true);
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

}
