/*
 * Created by JFormDesigner on Sat Mar 27 21:13:31 CST 2021
 */

package com.melo.notes.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @description 用户界面
 */
public class UserView extends JFrame {

    /**
     * 新增笔记
     */
    private final String ADDNOTE="新增笔记";

    public UserView() {
        initComponents();
        setSize(1300, 800);
        setLocation(330,120);
    }

    private void optionActionPerformed(ActionEvent e) {
        String  selectedNoteOption = (String)(noteOption.getSelectedItem());
        switch (selectedNoteOption){
            case ADDNOTE:
                System.out.println(111);
                break;
            default:
                System.out.println(222);
                break;
        }

        if(noteOption.getSelectedItem()==ADDNOTE){
            new LoginView().setVisible(true);
        }
    }

    /**
     * 新增笔记按钮
     * @param e
     */
    private void addNoteActionPerformed(ActionEvent e) {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        note = new JMenu();
        searchNote = new JMenuItem();
        addNote = new JMenuItem();
        deleteNote = new JMenuItem();
        noteOption = new JComboBox<>();

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

        //---- noteOption ----
        noteOption.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u65b0\u589e\u7b14\u8bb0",
            "\u67e5\u770b\u7b14\u8bb0"
        }));
        noteOption.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 18));
        noteOption.addActionListener(e -> optionActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(195, 195, 195)
                    .addComponent(noteOption, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(654, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(135, 135, 135)
                    .addComponent(noteOption, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(421, Short.MAX_VALUE))
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
    private JComboBox<String> noteOption;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
