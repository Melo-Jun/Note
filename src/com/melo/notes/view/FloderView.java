/*
 * Created by JFormDesigner on Wed Mar 31 19:23:44 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.dao.impl.FolderDaoImpl;
import com.melo.notes.dao.impl.GroupDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import static com.melo.notes.util.JdbcUtils.close;

/**
 * @author 1
 */
public class FloderView extends JFrame {

    private static  User author=null;
    //ѡ�е�����
    public static String selectedName ="";
    //ѡ�еĶ���(���ݿ�)
    public static String selectedObject="";
    private Listener l = new Listener();
    private JButton delete = new JButton("ɾ��");
    private JButton submit = new JButton("ȷ��");
    private JButton update = new JButton("�޸�");

    /**
     * ������ز�������
     * @return
     */
    FolderDaoImpl folderDao = (FolderDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.FolderDao);
    GroupDaoImpl groupDao = (GroupDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.GroupDao);

    public String getLocatedGroup() {
        return selectedName;
    }

    public FloderView(User user) {

            author=user;
            JFrame jf = new JFrame("���Դ���");
            jf.setSize(1300, 800);
            jf.setLocation(330, 120);
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setLocation(950, 400);


            submit.setBounds(700,400,70,20);
            delete.setBounds(700,350,70,20);
            update.setBounds(700,300,70,20);

        /**
         * ���Ӽ�����
         */
        delete.addMouseListener(l);
        update.addMouseListener(l);
        submit.addMouseListener(l);

        /**
         * ���������
         */
            panel.add(delete);
            panel.add(update);


        /**
         * �������ڵ�
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("֪ʶ��");

        /**
         * ����֪ʶ����������Ӧ�ʼǷ���
         */
        ResultSet FolderRs = folderDao.showNoteFolder(user);
        ResultSet GroupRs=null;
            try {
                while (FolderRs.next()) {
                    Object folderName = FolderRs.getObject("folder_name");
                    DefaultMutableTreeNode folder = new DefaultMutableTreeNode(folderName);
                    rootNode.add(folder);
                    GroupRs = groupDao.showNoteGroup(folderName.toString());
                    while(GroupRs.next()){
                        Object groupName = GroupRs.getObject("group_name");
                        DefaultMutableTreeNode group = new DefaultMutableTreeNode(groupName);
                        folder.add(group);
                    }
                }
            }catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    close(null, FolderRs);
                    close(null,GroupRs);
                }

            // ʹ�ø��ڵ㴴�������
            JTree tree = new JTree(rootNode);

            // ��������ʾ���ڵ���
            tree.setShowsRootHandles(true);

            // �������ڵ�ɱ༭
            tree.setEditable(true);


        /**
         * ���ýڵ�ѡ�м�����(��ѡ�еĽڵ����Ʊ�������)
         *
          */
        tree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    selectedName = e.getPath().getLastPathComponent().toString();
                    System.out.println(e.getPath().getPathCount());
                    System.out.println(selectedName);
                }
            });


            // �ڵ���ɾ�ļ�����
            tree.getModel().addTreeModelListener(new TreeModelListener() {
                @Override
                public void treeNodesChanged(TreeModelEvent e) {
                    System.out.println("�ڵ�ı�: " + e.getTreePath().getLastPathComponent());
                }
                @Override
                public void treeNodesInserted(TreeModelEvent e) {
                    System.out.println("�ڵ����: " + e.getTreePath());
                }
                @Override
                public void treeNodesRemoved(TreeModelEvent e) {
                    System.out.println("�ڵ��Ƴ�: " + e.getTreePath());
                }
                @Override
                public void treeStructureChanged(TreeModelEvent e) {
                    System.out.println("�ṹ�ı�: " + e.getTreePath());
                }
            });


            // ����������壬����������Ϊ���ڵ�չ���������Ҫ�ܴ�Ŀռ�����ʾ��������Ҫ��һ�����������������
            JScrollPane scrollPane = new JScrollPane(tree);
            scrollPane.setBounds(50,50,500,450);

            // ��ӹ�����嵽�������
            panel.add(scrollPane);
            panel.add(submit);

            // ���ô���������岢��ʾ
            jf.setContentPane(panel);
            jf.setVisible(true);
        }

    public class Listener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==delete){
                folderDao.deleteFolder(selectedName);
                new FloderView(author);
            }
            if(e.getSource()==update){
                String updateName = JOptionPane.showInputDialog("�������޸�����");

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
