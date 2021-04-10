/*
 * Created by JFormDesigner on Tue Apr 06 14:55:41 CST 2021
 */

package com.melo.notes.view;

import java.awt.event.*;

import com.melo.notes.bean.AuthorBean;
import com.melo.notes.bean.NoteBean;
import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Note;
import com.melo.notes.service.impl.NoteServiceImpl;
import com.melo.notes.service.impl.TableServiceImpl;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.util.StringUtils;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author Jun
 */
public class TableView extends JFrame {

    private final String PUBLIC="公开";
    private Note allNote=new Note();
    /**
     * 创建相关操作对象
     * @return
     */
    private final NoteServiceImpl noteService =(NoteServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.NoteService);
    private final TableServiceImpl tableService =(TableServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.TableViewService);
    private DefaultTableModel model= null;
    private JTable table=null;

    public TableView() {
        initComponents();
        setVisible(true);
        allNote.setAccess("公开");
        fillTable(allNote);
    }

    /**
     * 表格监听器
     * @param e
     */
    private void table1MouseClicked(MouseEvent e) {
        Integer row=table.getSelectedRow();
        if(row!=-1) {
            String authorId = (String) model.getValueAt(row, 2);
            String authorName = tableService.showNoteAuthor(authorId);
            String groupId = (String) model.getValueAt(row, 5);
            System.out.println(groupId + "笔记id");
            String groupName = tableService.showGroupName(groupId);
            authorFolder.setText("作者名称:" + authorName + "\n" + "笔记分组名称:" + groupName);
        }
    }

    /**
     * 查看笔记详情
     * @param e
     */
    private void noteTextActionPerformed(ActionEvent e) {
        Integer row=table.getSelectedRow();
        if(row!=-1) {
            Object noteId = model.getValueAt(row, 0);
            Note note = new Note();
            note.setId(noteId.toString());
            new NoteTextView(noteService.showNoteText(note));
        }
    }

    /**
     * 通过标题搜索
     * @param e
     */
    private void searchByTitleActionPerformed(ActionEvent e) {
        String title = JOptionPane.showInputDialog("请输入标题");
        NoteBean noteBean = new NoteBean(PUBLIC,title);
        fillTable(noteBean);
    }

    /**
     * 通过作者id搜索
     * @param e
     */
    private void searchByAuthorIdActionPerformed(ActionEvent e) {
        String authorId = JOptionPane.showInputDialog("请输入作者id");
        AuthorBean authorBean = new AuthorBean(PUBLIC,authorId);
        fillTable(authorBean);
    }

    /**
     * 返回刷新按钮
     * @param e
     */
    private void backActionPerformed(ActionEvent e) {
        fillTable(allNote);
    }

    /**
     * 填充表格
     * @param obj 笔记对象
     */
    private void fillTable(Object obj){
        model= (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        LinkedList<Note> notes = new NoteDaoImpl().showNoteAll(obj);
        for(Note tempNote:notes) {
            model.addRow(tableService.fillTable(tempNote));
        }
    }

    /**
     * 点赞按钮事件
     * @param e
     */
    private void likeActionPerformed(ActionEvent e) {
        Integer row=table.getSelectedRow();
        if(row!=-1) {
            String id = (String) model.getValueAt(row, 0);
            String likeCount = (String) model.getValueAt(row, 4);
            String updateLikeCount = StringUtils.increaseOne(likeCount);
            if (tableService.increaseLikeCount(updateLikeCount, id)) {
                JOptionPane.showMessageDialog(null, "操作成功");
                fillTable(allNote);
            } else {
                JOptionPane.showMessageDialog(null, "操作失败");
            }
        }
    }

    /**
     * 取消点赞按钮事件
     * @param e
     */
    private void withdrawLikeActionPerformed(ActionEvent e) {
        Integer row=table.getSelectedRow();
        if(row!=-1) {
            String id = (String) model.getValueAt(row, 0);
            String likeCount = (String) model.getValueAt(row, 4);
            String updateLikeCount = StringUtils.decreaseOne(likeCount);
            if (tableService.decreaseLikeCount(updateLikeCount, id)) {
                JOptionPane.showMessageDialog(null, "操作成功");
                fillTable(allNote);
            } else {
                JOptionPane.showMessageDialog(null, "操作失败");
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table = new JTable();
        scrollPane2 = new JScrollPane();
        authorFolder = new JTextArea();
        label1 = new JLabel();
        like = new JButton();
        withdrawLike = new JButton();
        noteText = new JButton();
        searchByTitle = new JButton();
        searchByAuthorId = new JButton();
        back = new JButton();

        //======== this ========
        setTitle("\u67e5\u770b\u7b14\u8bb0");
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {
            scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //---- table ----
            table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "id", "title", "authorId", "access", "likeCount", "locatedGroup"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    true, true, true, true, false, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            table.setFillsViewportHeight(true);
            table.setAlignmentY(1.0F);
            table.setAutoCreateRowSorter(true);
            table.setPreferredScrollableViewportSize(new Dimension(450, 500));
            table.setShowVerticalLines(false);
            table.setShowHorizontalLines(false);
            table.setRowHeight(20);
            table.setAlignmentX(1.0F);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table);
        }

        //======== scrollPane2 ========
        {

            //---- authorFolder ----
            authorFolder.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            scrollPane2.setViewportView(authorFolder);
        }

        //---- label1 ----
        label1.setText("\u7559\u4e0b\u4f60\u7684like\u5728\u6b64");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- like ----
        like.setText("\u70b9\u8d5e");
        like.addActionListener(e -> likeActionPerformed(e));

        //---- withdrawLike ----
        withdrawLike.setText("\u53d6\u6d88\u70b9\u8d5e");
        withdrawLike.addActionListener(e -> withdrawLikeActionPerformed(e));

        //---- noteText ----
        noteText.setText("\u67e5\u770b\u7b14\u8bb0\u5185\u5bb9");
        noteText.addActionListener(e -> noteTextActionPerformed(e));

        //---- searchByTitle ----
        searchByTitle.setText("\u6839\u636e\u6807\u9898\u641c\u7d22");
        searchByTitle.addActionListener(e -> searchByTitleActionPerformed(e));

        //---- searchByAuthorId ----
        searchByAuthorId.setText("\u6839\u636e\u4f5c\u8005id\u641c\u7d22");
        searchByAuthorId.addActionListener(e -> searchByAuthorIdActionPerformed(e));

        //---- back ----
        back.setText("\u8fd4\u56de");
        back.addActionListener(e -> backActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(like)
                            .addGap(18, 18, 18)
                            .addComponent(withdrawLike)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(noteText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(back, GroupLayout.Alignment.TRAILING)
                        .addComponent(searchByTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchByAuthorId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(25, 25, 25))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label1)
                                .addComponent(noteText))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(like)
                                    .addComponent(withdrawLike))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(searchByTitle)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(searchByAuthorId)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(back))
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    //private JTable table;
    private JScrollPane scrollPane2;
    private JTextArea authorFolder;
    private JLabel label1;
    private JButton like;
    private JButton withdrawLike;
    private JButton noteText;
    private JButton searchByTitle;
    private JButton searchByAuthorId;
    private JButton back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}