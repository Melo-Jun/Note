

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
    private final String NOTE="笔记";
    private final String GROUP="笔记分组";

    JFrame jf = new JFrame("设置笔记分组");
    private Listener l = new Listener();
    private JButton delete = new JButton("删除");
    private JButton flush = new JButton("刷新");
    private JButton addFolder = new JButton("新增知识库");
    private JButton addGroup = new JButton("新增笔记分组");
    private JButton update = new JButton("修改名称");
    private JButton setGroup = new JButton("设置笔记分组");
    private JButton updateNote = new JButton("设置笔记");
    private JButton addNote=new JButton("新增笔记");

    /**
     * 创建相关操作对象
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);
    NoteServiceImpl noteService =(NoteServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.NoteService);


    public String getLocatedGroup() {
        return selectedName;
    }

    public FolderView(User user) {

            //初始化知识库
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
          增加监听器
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
          加入面板中
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
          创建根节点
         */
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("知识库");

        /*
          根据知识库名生成相应笔记分组
         */
        HashMap<Object, Object> Folder = folderGroupService.showFolderName();
                /*
                  遍历获取folderId和folderName
                 */
                Set<Map.Entry<Object, Object>> folderSet =  Folder.entrySet();
                for(Map.Entry tempFolder:folderSet) {
                    Object folderId = tempFolder.getKey();
                    Object folderName = tempFolder.getValue();
                    /**
                     * 根据folderName生成知识库
                     */
                    DefaultMutableTreeNode folder = new DefaultMutableTreeNode(folderName);
                    rootNode.add(folder);
                    /**
                     * 再根据folderId生成相应笔记分组
                     */
                    HashMap<Object, Object> Group = folderGroupService.showNoteGroup(folderId.toString());
                    /**
                     * 遍历获取groupId和groupName
                     */
                    Set<Map.Entry<Object, Object>> groupSet =  Group.entrySet();
                    for(Map.Entry tempGroup:groupSet) {
                        Object groupId = tempGroup.getKey();
                        Object groupName = tempGroup.getValue();
                        DefaultMutableTreeNode group = new DefaultMutableTreeNode(groupName);
                        folder.add(group);
                        /**
                         * 最后根据groupId生成相应笔记
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
                        //判空
                       /* if(notes[0]!=null) {
                            for (int i = 0; i < notes.length&&notes[i]!=null; i++) {
                                DefaultMutableTreeNode noteTree = new DefaultMutableTreeNode(notes[i]);
                                group.add(noteTree);
                            }
                        }*/
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
                    selectedType = folderGroupService.judgeType(e.getPath().getPathCount());
                    System.out.println(selectedName+selectedType);
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
                if (JOptionPane.showConfirmDialog(null,
                        "确认删除请按是", "确认删除", JOptionPane.YES_NO_OPTION)==0) {
                    if (folderGroupService.delete(selectedName, selectedType) != 0) {
                        jf.dispose();
                        new FolderView(LoginView.USER);
                    } else {
                        JOptionPane.showMessageDialog(null, "请确认已正确选择节点");
                    }
                }
            }

            /*
             **************************************************************
             *              修改操作
             **************************************************************
             */

            //修改名称
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
            //修改笔记内容
            if(e.getSource()==updateNote){
                if(selectedType.equals(NOTE)) {
                    new UpdateNoteView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"请确认你选中的是笔记");
                }
            }
            //新增笔记
            if(e.getSource()==addNote){
                if(selectedType.equals(GROUP)){
                    new AddNoteView(LoginView.USER);
                }else {
                    JOptionPane.showMessageDialog(null,"请确认你选中的是待加入笔记的笔记分组");
                }
            }
            //设置笔记分组
            if(e.getSource()==setGroup){
                new SetGroupView().setVisible(true);
            }
            //增加知识库操作
            if(e.getSource()== addFolder){
               new AddFolderView().setVisible(true);
            }
            //增加笔记分组操作
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
