/*
 * Created by JFormDesigner on Wed Mar 31 19:23:44 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.util.BeanFactory;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Jun
 * @program Note
 * @description �����ʼ�ʱ���ñʼǷ������
 * @date 2021-4
 */
public class TreeView extends JFrame {
    /**
     * ѡ�е������Լ���Ӧid
     */
    public static String selectedName ="";
    public static String selectedId ="";
    /**
     * ѡ�е���
     */
    public static String selectedClassName ="";

    /**
     * ������
     */
    private Listener l = new Listener();
    private JButton submit = new JButton("ȷ��");
    JFrame jf = new JFrame("���Դ���");
    JPanel panel = new JPanel(null);

    /**
     * ������ز�������
     * @return
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    public TreeView() {

        jf.setSize(650, 650);
        jf.setLocation(1100, 250);
        jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        panel.setLocation(950, 400);


        submit.setBounds(200,500,70,20);

        /**
         * ���Ӽ�����
         */
        submit.addMouseListener(l);

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
                Object groupName = tempGroup.getValue();
                DefaultMutableTreeNode group = new DefaultMutableTreeNode(groupName);
                folder.add(group);
            }
        }

        // ʹ�ø��ڵ㴴�������
        JTree tree = new JTree(rootNode);

        // ��������ʾ���ڵ���
        tree.setShowsRootHandles(true);

        // �������ڵ�ɱ༭
        tree.setEditable(true);


        /**
         * ���ýڵ�ѡ�м�����(��ѡ�еĽڵ����ƺͶ����ౣ������)
         *
         */
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                selectedName = e.getPath().getLastPathComponent().toString();
                selectedClassName = folderGroupService.judgeType(e.getPath().getPathCount());
            }
        });

        // ����������壬����������Ϊ���ڵ�չ���������Ҫ�ܴ�Ŀռ�����ʾ��������Ҫ��һ�����������������
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBounds(50,50,500,450);

        // ���ӹ�����嵽�������
        panel.add(scrollPane);
        panel.add(submit);

        // ���ô���������岢��ʾ
        jf.setContentPane(panel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    /**
     * Ϊ���������Ӽ�����
     */
    public class Listener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            /**
             * ���ӱʼ�ʱѡ��ʼǷ���
             */
            if(e.getSource()==submit){
                if(folderGroupService.isGroup(selectedClassName)) {
                    JOptionPane.showMessageDialog(null,"ѡ��ɹ�");
                    jf.dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"��ȷ������ȷѡ��ʼǷ���");
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        //======== this ========
        Container contentPane = getContentPane();

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGap(0, 300, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}