/*
 * Created by JFormDesigner on Wed Mar 31 19:23:44 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.entity.User;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
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

import static com.melo.notes.util.JdbcUtils.close;

/**
 * @author Jun
 */
public class TreeView extends JFrame {
    /**
     * 用户
     */
    User user=LoginView.USER;
    /**
     * 选中的名称
     */
    public static String selectedName ="";
    /**
     * 选中的类
     */
    public static String selectedClassName ="";

    /**
     * 相关组件
     */
    private Listener l = new Listener();
    private JButton submit = new JButton("确定");
    JFrame jf = new JFrame("测试窗口");
    JPanel panel = new JPanel(null);

    /**
     * 创建相关操作对象
     * @return
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    public String getLocatedGroup() {
        return selectedName;
    }

    public TreeView() {

        jf.setSize(1300, 800);
        jf.setLocation(330, 120);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.setLocation(950, 400);


        submit.setBounds(700,400,70,20);

        /**
         * 增加监听器
         */
        submit.addMouseListener(l);

        /**
         * 加入面板中
         */


        /**
         * 创建根节点
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("知识库");

        /**
         * 根据知识库名生成相应笔记分组
         */
        ResultSet FolderRs = folderGroupService.showNoteFolder(user);
        System.out.println(FolderRs);
        System.out.println(user.getId());
        ResultSet GroupRs=null;
        try {
            /**
             * 先生成知识库
             */
            while (FolderRs.next()) {
                Object folderName = FolderRs.getObject("folder_name");
                Object id = FolderRs.getObject("id");
                System.out.println(id);
                DefaultMutableTreeNode folder = new DefaultMutableTreeNode(folderName);
                rootNode.add(folder);
                /**
                 * 再生成相应笔记分组
                 */
                GroupRs = folderGroupService.showNoteGroup(id.toString());
                System.out.println(GroupRs);
                while(GroupRs.next()){
                    Object groupName = GroupRs.getObject("group_name");
                    System.out.println(groupName.toString());
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
         * 设置节点选中监听器(将选中的节点名称和对象类保存起来)
         *
         */
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                selectedName = e.getPath().getLastPathComponent().toString();
                selectedClassName = folderGroupService.judgeClass(e.getPath().getPathCount());
                System.out.println(selectedClassName);
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

    /**
     * 为各操作添加监听器
     */
    public class Listener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            /**
             * 添加笔记时选择笔记分组
             */
            if(e.getSource()==submit){
                if(folderGroupService.isGroup(selectedClassName)) {
                    JOptionPane.showMessageDialog(null,"选择成功");
                    jf.dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"请确认已正确选择笔记分组");
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
