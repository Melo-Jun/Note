/*
 * Created by JFormDesigner on Sat Mar 27 20:56:15 CST 2021
 */

package com.melo.notes.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * @author Jun
 */
public class TreeView extends JFrame {

    public static String locatedGroup="";

    public String getLocatedGroup() {
        return locatedGroup;
    }

    public TreeView() {
        //initComponents();
        JFrame jf = new JFrame("测试窗口");
        JButton submit = new JButton("确定");
        jf.setSize(300, 300);
        jf.setLocation(1100, 400);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        panel.setLocation(800, 400);
        panel2.setLocation(800, 500);
        //panel.setLocation();

        // 创建根节点
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("中国");
        DefaultMutableTreeNode rootNode2 = new DefaultMutableTreeNode("中");

        // 创建二级节点
        DefaultMutableTreeNode gdNode = new DefaultMutableTreeNode("广东");
        DefaultMutableTreeNode fjNode = new DefaultMutableTreeNode("福建");
        DefaultMutableTreeNode shNode = new DefaultMutableTreeNode("上海");
        DefaultMutableTreeNode twNode = new DefaultMutableTreeNode("台湾");

        // 把二级节点作为子节点添加到根节点
        rootNode.add(gdNode);
        rootNode.add(fjNode);
        rootNode.add(shNode);
        rootNode.add(twNode);

        // 创建三级节点
        DefaultMutableTreeNode gzNode = new DefaultMutableTreeNode("广州");
        DefaultMutableTreeNode szNode = new DefaultMutableTreeNode("深圳");

        DefaultMutableTreeNode fzNode = new DefaultMutableTreeNode("福州");
        DefaultMutableTreeNode xmNode = new DefaultMutableTreeNode("厦门");

        DefaultMutableTreeNode tbNode = new DefaultMutableTreeNode("台北");
        DefaultMutableTreeNode gxNode = new DefaultMutableTreeNode("高雄");
        DefaultMutableTreeNode jlNode = new DefaultMutableTreeNode("基隆");

        // 把三级节点作为子节点添加到相应的二级节点
        gdNode.add(gzNode);
        gdNode.add(szNode);

        fjNode.add(fzNode);
        fjNode.add(xmNode);

        twNode.add(tbNode);
        twNode.add(gxNode);
        twNode.add(jlNode);


        //测试四级节点
        DefaultMutableTreeNode gzNode2 = new DefaultMutableTreeNode("广州南");
        gzNode.add(gzNode2);

        // 使用根节点创建树组件
        JTree tree = new JTree(rootNode);
        JTree tree2 = new JTree(rootNode2);

        // 设置树显示根节点句柄
        tree.setShowsRootHandles(true);
        tree2.setShowsRootHandles(true);

        // 设置树节点可编辑
        tree.setEditable(true);
        tree2.setShowsRootHandles(true);

        // 设置节点选中监听器
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                locatedGroup= e.getPath().getLastPathComponent().toString();
                System.out.println(locatedGroup);
            }
        });


        // 创建滚动面板，包裹树（因为树节点展开后可能需要很大的空间来显示，所以需要用一个滚动面板来包裹）
        JScrollPane scrollPane = new JScrollPane(tree);
        JScrollPane scrollPane2 = new JScrollPane(tree2);

        // 添加滚动面板到内容面板
        panel.add(scrollPane);
        panel2.add(scrollPane2);
        panel.add(submit, BorderLayout.SOUTH);

        // 设置窗口内容面板并显示
        jf.setContentPane(panel);
        jf.setVisible(true);
    }

    /**
     * 确定按钮事件
     */
   /* private void submitActionPerformed(ActionEvent e1,TreeSelectionEvent e2) {
        e2.getPath()
    }*/


    private void initComponents () {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            scrollPane1 = new JScrollPane();
            tree1 = new JTree();

            //======== this ========
            Container contentPane = getContentPane();

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(tree1);
            }

            GroupLayout contentPaneLayout = new GroupLayout(contentPane);
            contentPane.setLayout(contentPaneLayout);
            contentPaneLayout.setHorizontalGroup(
                    contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(123, 123, 123)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(197, Short.MAX_VALUE))
            );
            contentPaneLayout.setVerticalGroup(
                    contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(67, Short.MAX_VALUE))
            );
            pack();
            setLocationRelativeTo(getOwner());
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        private JScrollPane scrollPane1;
        private JTree tree1;
        // JFormDesigner - End of variables declaration  //GEN-END:variables
}
