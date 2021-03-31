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
    //选中的名称
    public static String selectedName ="";
    //选中的对象(数据库)
    public static String selectedObject="";
    private Listener l = new Listener();
    private JButton delete = new JButton("删除");
    private JButton submit = new JButton("确定");
    private JButton update = new JButton("修改");

    /**
     * 创建相关操作对象
     * @return
     */
    FolderDaoImpl folderDao = (FolderDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.FolderDao);
    GroupDaoImpl groupDao = (GroupDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.GroupDao);

    public String getLocatedGroup() {
        return selectedName;
    }

    public FloderView(User user) {

            author=user;
            JFrame jf = new JFrame("测试窗口");
            jf.setSize(1300, 800);
            jf.setLocation(330, 120);
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null);
            panel.setLocation(950, 400);


            submit.setBounds(700,400,70,20);
            delete.setBounds(700,350,70,20);
            update.setBounds(700,300,70,20);

        /**
         * 增加监听器
         */
        delete.addMouseListener(l);
        update.addMouseListener(l);
        submit.addMouseListener(l);

        /**
         * 加入面板中
         */
            panel.add(delete);
            panel.add(update);


        /**
         * 创建根节点
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("知识库");

        /**
         * 根据知识库名生成相应笔记分组
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

            // 使用根节点创建树组件
            JTree tree = new JTree(rootNode);

            // 设置树显示根节点句柄
            tree.setShowsRootHandles(true);

            // 设置树节点可编辑
            tree.setEditable(true);


        /**
         * 设置节点选中监听器(将选中的节点名称保存起来)
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


            // 节点增删改监听器
            tree.getModel().addTreeModelListener(new TreeModelListener() {
                @Override
                public void treeNodesChanged(TreeModelEvent e) {
                    System.out.println("节点改变: " + e.getTreePath().getLastPathComponent());
                }
                @Override
                public void treeNodesInserted(TreeModelEvent e) {
                    System.out.println("节点插入: " + e.getTreePath());
                }
                @Override
                public void treeNodesRemoved(TreeModelEvent e) {
                    System.out.println("节点移除: " + e.getTreePath());
                }
                @Override
                public void treeStructureChanged(TreeModelEvent e) {
                    System.out.println("结构改变: " + e.getTreePath());
                }
            });


            // 创建滚动面板，包裹树（因为树节点展开后可能需要很大的空间来显示，所以需要用一个滚动面板来包裹）
            JScrollPane scrollPane = new JScrollPane(tree);
            scrollPane.setBounds(50,50,500,450);

            // 添加滚动面板到内容面板
            panel.add(scrollPane);
            panel.add(submit);

            // 设置窗口内容面板并显示
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
                String updateName = JOptionPane.showInputDialog("请输入修改名称");

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
