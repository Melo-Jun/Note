/*
 * Created by JFormDesigner on Sat Mar 27 21:13:31 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.entity.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Jun
 * @program Note
 * @description 用户界面
 * @date 2021-4
 */
public class UserView extends JFrame {

    public UserView(User user) {
        initComponents();
        setVisible(true);
        welcome.setText("欢迎回来, "+LoginView.USER.getUserName());
        setSize(650, 650);
        setLocation(600, 260);
    }

    /**
     * 各按钮事件
     *
     * @param e
     */
    private void addNoteActionPerformed(ActionEvent e) {
        new AddNoteView(LoginView.USER);
    }

    private void setNoteActionPerformed(ActionEvent e) {
        new FolderView(LoginView.USER);
    }

    private void showNoteActionPerformed(ActionEvent e) {
        new NoteTableView();
    }

    private void updateActionPerformed(ActionEvent e) {
        new PersonalUserView();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        note = new JMenu();
        showNote = new JMenuItem();
        addNote = new JMenuItem();
        setNote = new JMenuItem();
        user = new JMenu();
        update = new JMenuItem();
        welcome = new JLabel();
        photo = new JLabel();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u7528\u6237\u754c\u9762");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
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
                showNote.setFont(new Font("Microsoft YaHei UI", showNote.getFont().getStyle() & ~Font.ITALIC, showNote.getFont().getSize() + 4));
                showNote.addActionListener(e -> showNoteActionPerformed(e));
                note.add(showNote);

                //---- addNote ----
                addNote.setText("\u589e\u52a0\u7b14\u8bb0");
                addNote.setFont(new Font("Microsoft YaHei UI", addNote.getFont().getStyle() & ~Font.ITALIC, addNote.getFont().getSize() + 4));
                addNote.addActionListener(e -> addNoteActionPerformed(e));
                note.add(addNote);

                //---- setNote ----
                setNote.setText("\u8bbe\u7f6e\u5206\u7ec4");
                setNote.setFont(new Font("Microsoft YaHei UI", setNote.getFont().getStyle() & ~Font.ITALIC, setNote.getFont().getSize() + 4));
                setNote.addActionListener(e -> setNoteActionPerformed(e));
                note.add(setNote);
            }
            menuBar.add(note);

            //======== user ========
            {
                user.setText("\u8bbe\u7f6e\u4e2a\u4eba\u4fe1\u606f");
                user.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- update ----
                update.setText("\u4fee\u6539\u7528\u6237\u540d\u5bc6\u7801");
                update.setFont(update.getFont().deriveFont(update.getFont().getSize() + 4f));
                update.addActionListener(e -> updateActionPerformed(e));
                user.add(update);
            }
            menuBar.add(user);
        }
        setJMenuBar(menuBar);

        //---- welcome ----
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setText("\u6b22\u8fce\u56de\u6765");
        welcome.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        welcome.setForeground(new Color(51, 153, 255));
        contentPane.add(welcome);
        welcome.setBounds(135, 85, 283, 116);

        //---- photo ----
        photo.setIcon(new ImageIcon(getClass().getResource("/img/UserView.jpg")));
        contentPane.add(photo);
        photo.setBounds(0, 15, 945, 575);

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
    private JMenu user;
    private JMenuItem update;
    private JLabel welcome;
    private JLabel photo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
