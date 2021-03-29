/*
 * Created by JFormDesigner on Sat Mar 27 21:13:31 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @description 用户界面
 */
public class UserView extends JFrame {

    public UserView(User user) {
        initComponents(user);
        setSize(1300, 800);
        setLocation(330,120);
    }


    /**
     * 新增笔记
     * @param e
     * @param user 用户
     */
    private void addNoteActionPerformed(ActionEvent e,User user) {
        new AddNoteView(user).setVisible(true);
    }

    /**
     * 查看笔记
     * @param e
     */
    private void searchNoteActionPerformed(ActionEvent e,User user) {
        ResultSet rs = new NoteDaoImpl().showNoteTitle(user);
        try {
            JLabel label = new JLabel();
            label.setText("haha");
            this.add(label);
            while (rs.next()) {
                String title = (String)rs.getObject("title");
                System.out.println(title);
                JLabel label2 = new JLabel();
                label2.setText(title);
                this.add(label2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void searchNoteActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void addNoteActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents(User user) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        note = new JMenu();
        searchNote = new JMenuItem();
        addNote = new JMenuItem();
        deleteNote = new JMenuItem();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== menuBar ========
        {
            menuBar.setName("\u65b0\u589e\u7b14\u8bb0");
            menuBar.setAlignmentY(0.8F);
            menuBar.setMinimumSize(new Dimension(59, 50));

            //======== note ========
            {
                note.setText("\u7ba1\u7406\u7b14\u8bb0");
                note.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- searchNote ----
                searchNote.setText("\u67e5\u770b\u7b14\u8bb0");
                searchNote.addActionListener(e -> searchNoteActionPerformed(e));
                note.add(searchNote);

                //---- addNote ----
                addNote.setText("\u589e\u52a0\u7b14\u8bb0");
                addNote.addActionListener(e -> addNoteActionPerformed(e));
                note.add(addNote);

                //---- deleteNote ----
                deleteNote.setText("\u5220\u9664\u7b14\u8bb0");
                note.add(deleteNote);
            }
            menuBar.add(note);
        }
        setJMenuBar(menuBar);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 948, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 589, Short.MAX_VALUE)
        );
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar;
    private JMenu note;
    private JMenuItem searchNote;
    private JMenuItem addNote;
    private JMenuItem deleteNote;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
