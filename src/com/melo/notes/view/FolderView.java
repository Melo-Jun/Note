

package com.melo.notes.view;

import com.melo.notes.entity.User;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedList;
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
     * 用户
     */
    User user=LoginView.USER;

    /**
     * 选中的名称
     */
    public static String selectedName ="";
    /**
     * 选中的类型
     */
    public static String selectedType ="";

    JFrame jf = new JFrame("设置笔记分组");
    private Listener l = new Listener();
    private JButton delete = new JButton("删除");
    private JButton flush = new JButton("刷新");
    private JButton addFolder = new JButton("新增知识库");
    private JButton addGroup = new JButton("新增笔记分组");
    private JButton update = new JButton("修改名称");
    private JButton setGroup = new JButton("设置笔记分组");

    /**
     * 创建相关操作对象
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

            flush.setBounds(600,200,120,50);
            update.setBounds(600,250,120,50);
            delete.setBounds(600,300,120,50);
            addFolder.setBounds(600,350,120,50);
            addGroup.setBounds(600,400,120,50);
            setGroup.setBounds(600,450,120,50);

        /**
         * 增加监听器
         */
        delete.addMouseListener(l);
        update.addMouseListener(l);
        addFolder.addMouseListener(l);
        addGroup.addMouseListener(l);
        setGroup.addMouseListener(l);
        flush.addMouseListener(l);

        /**
         * 加入面板中
         */
            panel.add(flush);
            panel.add(delete);
            panel.add(update);
            panel.add(setGroup);
            panel.add(addFolder);
            panel.add(addGroup);

        /**
         * 创建根节点
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("知识库");

        /**
         * 根据知识库名生成相应笔记分组
         */
        User tempUser = new User();
        tempUser.setId(LoginView.USER.getId());
        HashMap<Object, Object> Folder = folderGroupService.showFolderName(tempUser);
                /**
                 * 先生成知识库
                 */
                Object folderId="";
                Object folderName="";
                /**
                 * 遍历获取folderId和folderName
                 */
                Set<Map.Entry<Object, Object>> entries =  Folder.entrySet();
                for(Map.Entry temp:entries) {
                    folderId = temp.getKey();
                    folderName = temp.getValue();
                    /**
                     * 根据folderName生成知识库
                     */
                    DefaultMutableTreeNode folder = new DefaultMutableTreeNode(folderName);
                    rootNode.add(folder);
                    /**
                     * 再根据folderId生成相应笔记分组
                     */
                    LinkedList<Object> groupNames = folderGroupService.showNoteGroup(folderId.toString());
                    for(Object groupName:groupNames) {
                        DefaultMutableTreeNode group = new DefaultMutableTreeNode(groupName);
                        folder.add(group);
                    }
                }

            // 使用根节点创建树组件
            JTree tree = new JTree(rootNode);

            // 设置树显示根节点句柄
            tree.setShowsRootHandles(true);

            // 设置树节点可编辑
            tree.setEditable(true);


        /**
         * 设置节点选中监听器(将选中的节点名称和对象类型保存起来)
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
             * 刷新操作
             */
            if(e.getSource()==flush){
                jf.dispose();
                new FolderView(LoginView.USER);
            }
            /**
             * 删除操作
             */
            if(e.getSource()==delete){
                if(folderGroupService.delete(selectedName, selectedType)!=0) {
                    JOptionPane.showConfirmDialog(null,"确定删除吗");
                    jf.dispose();
                    new FolderView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"请确认已正确选择节点");
                }
            }
            /**
             * 修改操作
             */
            if(e.getSource()==update){
                String updateName = JOptionPane.showInputDialog("请输入修改名称");
                if(folderGroupService.update(selectedName,updateName,selectedType)!=0){
                    JOptionPane.showMessageDialog(null,"修改成功");
                    jf.dispose();
                    new FolderView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"修改失败");
                }
            }
            /**
             * 设置笔记分组
             */
            if(e.getSource()==setGroup){
                new setGroupView().setVisible(true);
            }
            /**
             * 增加知识库操作
             */
            if(e.getSource()== addFolder){
               new AddFolderView().setVisible(true);
            }
            /**
             * 增加笔记分组操作
             */
            if(e.getSource()==addGroup){
                 new AddGroupView().setVisible(true);
                 }
            /**
             * 设置笔记分组操作
             */
            if(e.getSource()==setGroup){

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
