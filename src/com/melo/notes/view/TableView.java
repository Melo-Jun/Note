/*
 * Created by JFormDesigner on Tue Apr 06 14:55:41 CST 2021
 */

package com.melo.notes.view;

import java.awt.event.*;

import com.melo.notes.bean.AuthorBean;
import com.melo.notes.bean.NoteBean;
import com.melo.notes.dao.impl.BaseDaoImpl;
import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Note;
import com.melo.notes.service.impl.ListNoteTitleServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.util.LinkedList;
import java.util.Vector;
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
    ListNoteTitleServiceImpl listNoteTitleService=(ListNoteTitleServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.ListNoteTitleService);

    DefaultTableModel model= null;

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
    }

    /**
     * 查看笔记详情
     * @param e
     */
    private void noteTextActionPerformed(ActionEvent e) {
        Integer row=table.getSelectedRow();
        Object valueAt = model.getValueAt(row, 5);
        Note note = new Note();
        note.setId(valueAt.toString());
        new NoteTextView(listNoteTitleService.showNoteText(note));
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
        /*Note note = new Note();
        note.setAuthorId(authorId);
        note.setAccess(PUBLIC);
        fillTable(note);*/
        fillTable(authorBean);
    }

    private void backActionPerformed(ActionEvent e) {
        fillTable(allNote);
    }

    private void fillTable(Object obj){
        //记录列数
        int count=0;
        model= (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        LinkedList<Object> notes = new NoteDaoImpl().showNoteAll(obj);
        for(Object tempNote:notes) {
            Vector<Object> objects = new Vector<>();
            /**
             * 获取对象属性值
             */
            LinkedList<Object> fieldNames = new LinkedList<>();
            LinkedList<Object> fieldValues = new LinkedList<>();
            new BaseDaoImpl().fieldMapper(tempNote, fieldNames, fieldValues);
            for (Object value : fieldValues) {
                count++;
                /**
                 * 过滤掉text内容,另外单独展示
                 */
                if (count != 4) {
                    objects.add(value);
                }
            }
            count=0;
            model.addRow(objects);
        }
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table = new JTable();
        scrollPane2 = new JScrollPane();
        authorFolder = new JTextArea();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
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
                    "title", "authorId", "access", "likeCount", "locatedGroup", "id"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    true, true, true, false, true, true
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
            scrollPane2.setViewportView(authorFolder);
        }

        //---- label1 ----
        label1.setText("\u7559\u4e0b\u4f60\u7684like\u5728\u6b64");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- button1 ----
        button1.setText("\u70b9\u8d5e");

        //---- button2 ----
        button2.setText("\u53d6\u6d88\u70b9\u8d5e");

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
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(button1)
                                    .addGap(18, 18, 18)
                                    .addComponent(button2)
                                    .addGap(6, 6, 6)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(searchByTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(noteText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchByAuthorId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(35, 35, 35))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(back)
                            .addGap(68, 68, 68))))
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
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label1)
                                .addComponent(noteText))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(searchByTitle)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button2)
                                        .addComponent(button1)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(searchByAuthorId)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(back)))
                    .addContainerGap(13, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table;
    private JScrollPane scrollPane2;
    private JTextArea authorFolder;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton noteText;
    private JButton searchByTitle;
    private JButton searchByAuthorId;
    private JButton back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}