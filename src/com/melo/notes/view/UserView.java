/*
 * Created by JFormDesigner on Sat Mar 27 21:13:31 CST 2021
 */

package com.melo.notes.view;

import javax.swing.event.*;
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
        welcome.setText("欢迎回来, "+LoginView.USER.getUserName());
        welcome.setBounds(650,100,150,20);
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
        welcome = new JLabel();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("\u7528\u6237\u754c\u9762");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

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

        //---- welcome ----
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setText("\u6b22\u8fce\u56de\u6765");
        welcome.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        welcome.setForeground(new Color(51, 153, 255));
        contentPane.add(welcome);
        welcome.setBounds(332, 110, 283, 116);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar;
    private JMenu note;
    private JMenuItem showNote;
    private JMenuItem addNote;
    private JMenuItem setNote;
    private JLabel welcome;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
