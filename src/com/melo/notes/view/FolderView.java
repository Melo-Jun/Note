

package com.melo.notes.view;

import com.melo.notes.entity.User;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.constant.TypeName;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.service.impl.NoteServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Jun
 * @program Note
 * @description ���ñʼǷ������
 * @date 2021-4
 */
public class FolderView extends JFrame {

    /**
     * �û�
     */
    User user=LoginView.USER;

    /**
     * ѡ�е�����
     */
    public static String selectedName =" ";
    /**
     * ѡ�е�����
     */
    public static String selectedType =" ";
    /**
     * ѡ�е�id
     */
    public static String selectedId=" ";

    JFrame jf = new JFrame("���ñʼǷ���");
    private Listener l = new Listener();
    private JButton delete = new JButton("ɾ��");
    private JButton flush = new JButton("ˢ��");
    private JButton addFolder = new JButton("����֪ʶ��");
    private JButton addGroup = new JButton("��������");
    private JButton update = new JButton("�޸�");
    private JButton setGroup = new JButton("Ǩ�Ʒ���");
    private JButton addNote=new JButton("�����ʼ�");
    private TextArea textArea=new TextArea();

    /**
     * ������ز�������
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);
    NoteServiceImpl noteService =(NoteServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.NoteService);

    public FolderView(User user) {

            //��ʼ��֪ʶ��
            folderGroupService.initFolderGroup();

            jf.setSize(800, 600);
            jf.setLocation(600, 260);
            jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setLocation(950, 400);

            textArea.setBounds(600,5,300,200);

            update.setBounds(100,5,100,40);
            setGroup.setBounds(200,5,120,40);
            delete.setBounds(400,5,120,40);

            flush.setBounds(600,200,120,50);

            addNote.setBounds(600,300,120,50);
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
            panel.add(addNote);
            panel.add(textArea);

        /*
          �������ڵ�
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("֪ʶ��");

        /*
          ����֪ʶ����������Ӧ�ʼǷ���
         */
        HashMap<Integer, Object> folder = folderGroupService.showFolderName();
        if(!folder.isEmpty()){
                /*
                  ������ȡfolderId��folderName
                 */
                Set<Map.Entry<Integer, Object>> folderSet =  folder.entrySet();
                    for (Map.Entry tempFolder : folderSet) {
                        Object folderId = tempFolder.getKey();
                        Object folderName = tempFolder.getValue();
                    /*
                      ����folderName����֪ʶ��
                     */
                        DefaultMutableTreeNode folderTree = new DefaultMutableTreeNode(folderId + "--" + folderName);
                        rootNode.add(folderTree);
                    /*
                      �ٸ���folderId������Ӧ�ʼǷ���
                     */
                        HashMap<Object, Object> group = folderGroupService.showNoteGroup(folderId.toString());
                        if(!group.isEmpty()) {
                    /*
                      ������ȡgroupId��groupName
                     */
                            Set<Map.Entry<Object, Object>> groupSet = group.entrySet();
                            for (Map.Entry tempGroup : groupSet) {
                                Object groupId = tempGroup.getKey();
                                Object groupName = tempGroup.getValue();
                                DefaultMutableTreeNode groupTree = new DefaultMutableTreeNode(groupId + "--" + groupName);
                                folderTree.add(groupTree);
                        /*
                          ������groupId������Ӧ�ʼ�
                         */
                                HashMap<Object, Object> note = noteService.showNoteTitle(groupId.toString());
                                if (!note.isEmpty()) {
                                    Set<Map.Entry<Object, Object>> noteSet = note.entrySet();
                                        for (Map.Entry tempNote:noteSet) {
                                            Object noteId=tempNote.getKey();
                                            Object noteName=tempNote.getValue();
                                            DefaultMutableTreeNode noteTree = new DefaultMutableTreeNode(noteId+"--"+noteName);
                                            groupTree.add(noteTree);
                                        }
                                    }
                                }
                            }
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
                    //���ڵ㲻����
                    if(!selectedName.equals(TypeName.FOLDER.getMessage())) {
                        //�ض�"--"֮ǰ��Ϊid,֮���Ϊ����
                        int location = selectedName.indexOf("-");
                        selectedId = selectedName.substring(0, location);
                        selectedName = selectedName.substring(location + 2);
                        selectedType = folderGroupService.judgeType(e.getPath().getPathCount());
                        textArea.setText("��ѡ�е�����Ϊ:" + selectedType+"\n\n'Ĭ��'�����޸Ļ�ɾ��\n�޸ĵȲ�����ѡ��Ŀ��\n�����ʼǷ�����ѡ��֪ʶ��" +
                                "\n�����ʼ���ѡ��ʼǷ���\nǨ�Ʒ�����ѡ��ʼǷ���");
                    }
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
                    if (folderGroupService.delete(selectedName, selectedType,selectedId) != 0) {
                        jf.dispose();
                        new FolderView(LoginView.USER);
                    } else {
                        JOptionPane.showMessageDialog(null, "��ȷ������ȷѡ��ڵ�");
                    }
            }

            /*
             **************************************************************
             *              �޸Ĳ���
             **************************************************************
             */

            //�޸ıʼǻ��޸�֪ʶ��ʼǷ�������
            if(e.getSource()==update) {
                //�޸ıʼǵ����
                if (selectedType.equals(TypeName.NOTE.getMessage())) {
                    new UpdateNoteView(LoginView.USER);
                } else {
                    if (folderGroupService.update(selectedName, selectedType) != 0) {
                        JOptionPane.showMessageDialog(null, Status.SUCCESS.getMessage());
                        jf.dispose();
                        new FolderView(LoginView.USER);
                    } else {
                        JOptionPane.showMessageDialog(null, Status.FAILED.getMessage());
                    }
                }
            }
            //���ñʼǷ���
            if(e.getSource()==setGroup){
                if(selectedType.equals(TypeName.GROUP.getMessage())) {
                    new SetGroupView();
                }else {
                    JOptionPane.showMessageDialog(null,"��ȷ����ѡ�е��ǱʼǷ���");
                }
            }
            //�����ʼ�
            if(e.getSource()==addNote){
                if(selectedType.equals(TypeName.GROUP.getMessage())){
                    new AddNoteView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"��ȷ����ѡ�е��Ǵ�����ʼǵıʼǷ���");
                }
            }
            //����֪ʶ�����
            if(e.getSource()== addFolder){
               new AddFolderView();
            }
            //���ӱʼǷ������
            if(e.getSource()==addGroup) {
                if(selectedType.equals(TypeName.FOLDER.getMessage())) {
                    new AddGroupView();
                }else {
                    JOptionPane.showMessageDialog(null,"��ȷ����ѡ�е��Ǵ�����ʼǷ����֪ʶ��");
                }
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
