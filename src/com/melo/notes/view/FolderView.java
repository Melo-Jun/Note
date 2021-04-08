

package com.melo.notes.view;

import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.service.impl.ListNoteTitleServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
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

    JFrame jf = new JFrame("���ñʼǷ���");
    private Listener l = new Listener();
    private JButton delete = new JButton("ɾ��");
    private JButton flush = new JButton("ˢ��");
    private JButton addFolder = new JButton("����֪ʶ��");
    private JButton addGroup = new JButton("�����ʼǷ���");
    private JButton update = new JButton("�޸�����");
    private JButton setGroup = new JButton("���ñʼǷ���");
    private JButton updateNote = new JButton("���ñʼ�");

    /**
     * ������ز�������
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    public String getLocatedGroup() {
        return selectedName;
    }

    public FolderView(User user) {


            jf.setSize(800, 600);
            jf.setLocation(330, 120);
            jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setLocation(950, 400);

            update.setBounds(100,5,100,40);
            setGroup.setBounds(200,5,120,40);
            updateNote.setBounds(300,5,120,40);

            flush.setBounds(600,200,120,50);

            delete.setBounds(600,300,120,50);
            addFolder.setBounds(600,350,120,50);
            addGroup.setBounds(600,400,120,50);

        /**
         * ���Ӽ�����
         */
        delete.addMouseListener(l);
        update.addMouseListener(l);
        addFolder.addMouseListener(l);
        addGroup.addMouseListener(l);
        setGroup.addMouseListener(l);
        flush.addMouseListener(l);
        updateNote.addMouseListener(l);

        /**
         * ���������
         */
            panel.add(flush);
            panel.add(delete);
            panel.add(update);
            panel.add(setGroup);
            panel.add(addFolder);
            panel.add(addGroup);
            panel.add(updateNote);

        /**
         * �������ڵ�
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("֪ʶ��");

        /**
         * ����֪ʶ����������Ӧ�ʼǷ���
         */
        HashMap<Object, Object> Folder = folderGroupService.showFolderName();
                /**
                 * ������ȡfolderId��folderName
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
                        note.setAccess("����");
                        note.setLocatedGroup(groupId.toString());
                        String[] notes = new ListNoteTitleServiceImpl().listNoteTitle(note);
                        //�п�
                        if(notes[0]!=null) {
                            for (int i = 0; i < notes.length&&notes[i]!=null; i++) {
                                DefaultMutableTreeNode noteTree = new DefaultMutableTreeNode(notes[i]);
                                group.add(noteTree);
                            }
                        }
                    }
                }

            // ʹ�ø��ڵ㴴�������
            JTree tree = new JTree(rootNode);

            // ��������ʾ���ڵ���
            tree.setShowsRootHandles(true);

            // �������ڵ�ɱ༭
            tree.setEditable(true);


        /**
         * ���ýڵ�ѡ�м�����(��ѡ�еĽڵ����ƺͶ������ͱ�������)
         *
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
            /**
             * ˢ�²���
             */
            if(e.getSource()==flush){
                jf.dispose();
                new FolderView(LoginView.USER);
            }
            /**
             * ɾ������
             */
            if(e.getSource()==delete){
                if(folderGroupService.delete(selectedName, selectedType)!=0) {
                    JOptionPane.showConfirmDialog(null,"ȷ��ɾ����");
                    jf.dispose();
                    new FolderView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"��ȷ������ȷѡ��ڵ�");
                }
            }
            /**
             * �޸Ĳ���
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
            /**
             * ���ñʼǷ���
             */
            if(e.getSource()==setGroup){
                new SetGroupView().setVisible(true);
            }
            /**
             * ����֪ʶ�����
             */
            if(e.getSource()== addFolder){
               new AddFolderView().setVisible(true);
            }
            /**
             * ���ӱʼǷ������
             */
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
