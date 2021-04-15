

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
 * @description 设置笔记分组界面
 * @date 2021-4
 */
public class FolderView extends JFrame {

    /**
     * 用户
     */
    User user=LoginView.USER;

    /**
     * 选中的名称
     */
    public static String selectedName =" ";
    /**
     * 选中的类型
     */
    public static String selectedType =" ";
    /**
     * 选中的id
     */
    public static String selectedId=" ";

    JFrame jf = new JFrame("设置笔记分组");
    private Listener l = new Listener();
    private JButton delete = new JButton("删除");
    private JButton flush = new JButton("刷新");
    private JButton addFolder = new JButton("新增知识库");
    private JButton addGroup = new JButton("新增分组");
    private JButton update = new JButton("修改");
    private JButton setGroup = new JButton("迁移分组");
    private JButton addNote=new JButton("新增笔记");
    private TextArea textArea=new TextArea();

    /**
     * 创建相关操作对象
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);
    NoteServiceImpl noteService =(NoteServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.NoteService);

    public FolderView(User user) {

            //初始化知识库
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
          增加监听器
         */
        delete.addMouseListener(l);
        update.addMouseListener(l);
        addFolder.addMouseListener(l);
        addGroup.addMouseListener(l);
        setGroup.addMouseListener(l);
        flush.addMouseListener(l);
        addNote.addMouseListener(l);

        /*
          加入面板中
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
          创建根节点
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("知识库");

        /*
          根据知识库名生成相应笔记分组
         */
        HashMap<Integer, Object> folder = folderGroupService.showFolderName();
        if(!folder.isEmpty()){
                /*
                  遍历获取folderId和folderName
                 */
                Set<Map.Entry<Integer, Object>> folderSet =  folder.entrySet();
                    for (Map.Entry tempFolder : folderSet) {
                        Object folderId = tempFolder.getKey();
                        Object folderName = tempFolder.getValue();
                    /*
                      根据folderName生成知识库
                     */
                        DefaultMutableTreeNode folderTree = new DefaultMutableTreeNode(folderId + "--" + folderName);
                        rootNode.add(folderTree);
                    /*
                      再根据folderId生成相应笔记分组
                     */
                        HashMap<Object, Object> group = folderGroupService.showNoteGroup(folderId.toString());
                        if(!group.isEmpty()) {
                    /*
                      遍历获取groupId和groupName
                     */
                            Set<Map.Entry<Object, Object>> groupSet = group.entrySet();
                            for (Map.Entry tempGroup : groupSet) {
                                Object groupId = tempGroup.getKey();
                                Object groupName = tempGroup.getValue();
                                DefaultMutableTreeNode groupTree = new DefaultMutableTreeNode(groupId + "--" + groupName);
                                folderTree.add(groupTree);
                        /*
                          最后根据groupId生成相应笔记
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

            //使用根节点创建树组件
            JTree tree = new JTree(rootNode);

            // 设置树显示根节点句柄
            tree.setShowsRootHandles(true);

            // 设置树节点可编辑
            tree.setEditable(true);


        /*
          设置节点选中监听器(将选中的节点名称和对象类型保存起来)
         */
        tree.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    selectedName = e.getPath().getLastPathComponent().toString();
                    //根节点不监听
                    if(!selectedName.equals(TypeName.FOLDER.getMessage())) {
                        //截断"--"之前的为id,之后的为名称
                        int location = selectedName.indexOf("-");
                        selectedId = selectedName.substring(0, location);
                        selectedName = selectedName.substring(location + 2);
                        selectedType = folderGroupService.judgeType(e.getPath().getPathCount());
                        textArea.setText("你选中的类型为:" + selectedType+"\n\n'默认'不可修改或删除\n修改等操作需选中目标\n新增笔记分组请选中知识库" +
                                "\n新增笔记请选择笔记分组\n迁移分组请选择笔记分组");
                    }
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
            //刷新操作
            if(e.getSource()==flush){
                jf.dispose();
                new FolderView(LoginView.USER);
            }
            //删除操作
            if(e.getSource()==delete) {
                    if (folderGroupService.delete(selectedName, selectedType,selectedId) != 0) {
                        jf.dispose();
                        new FolderView(LoginView.USER);
                    } else {
                        JOptionPane.showMessageDialog(null, "请确认已正确选择节点");
                    }
            }

            /*
             **************************************************************
             *              修改操作
             **************************************************************
             */

            //修改笔记或修改知识库笔记分组名称
            if(e.getSource()==update) {
                //修改笔记的情况
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
            //设置笔记分组
            if(e.getSource()==setGroup){
                if(selectedType.equals(TypeName.GROUP.getMessage())) {
                    new SetGroupView();
                }else {
                    JOptionPane.showMessageDialog(null,"请确认你选中的是笔记分组");
                }
            }
            //新增笔记
            if(e.getSource()==addNote){
                if(selectedType.equals(TypeName.GROUP.getMessage())){
                    new AddNoteView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"请确认你选中的是待加入笔记的笔记分组");
                }
            }
            //增加知识库操作
            if(e.getSource()== addFolder){
               new AddFolderView();
            }
            //增加笔记分组操作
            if(e.getSource()==addGroup) {
                if(selectedType.equals(TypeName.FOLDER.getMessage())) {
                    new AddGroupView();
                }else {
                    JOptionPane.showMessageDialog(null,"请确认你选中的是待加入笔记分组的知识库");
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
