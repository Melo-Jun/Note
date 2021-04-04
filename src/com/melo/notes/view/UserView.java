/*
 * Created by JFormDesigner on Sat Mar 27 21:13:31 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.entity.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @description 用户界面
 */
public class UserView extends JFrame {

    public UserView(User user) {
        initComponents();
        setSize(1300, 800);
        setLocation(330, 120);
    }

    /**
     * 各按钮事件
     *
     * @param e
     */
    private void addNoteActionPerformed(ActionEvent e) {
        new AddNoteView(LoginView.USER).setVisible(true);
    }

    private void setNoteActionPerformed(ActionEvent e) {
        new FolderView(LoginView.USER);
    }

    private void showNoteActionPerformed(ActionEvent e) {
        new ListNoteTitle();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        note = new JMenu();
        showNote = new JMenuItem();
        addNote = new JMenuItem();
        setNote = new JMenuItem();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
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

                //---- showNote ----
                showNote.setText("\u67e5\u770b\u7b14\u8bb0");
                showNote.addActionListener(e -> showNoteActionPerformed(e));
                note.add(showNote);

                //---- addNote ----
                addNote.setText("\u589e\u52a0\u7b14\u8bb0");
                addNote.addActionListener(e -> addNoteActionPerformed(e));
                note.add(addNote);

                //---- setNote ----
                setNote.setText("\u8bbe\u7f6e\u5206\u7ec4");
                setNote.addActionListener(e -> setNoteActionPerformed(e));
                note.add(setNote);
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
    private JMenuItem showNote;
    private JMenuItem addNote;
    private JMenuItem setNote;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
