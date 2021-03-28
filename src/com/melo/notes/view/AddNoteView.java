/*
 * Created by JFormDesigner on Sun Mar 28 16:42:06 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 */
public class AddNoteView extends JFrame {
    public AddNoteView(User user) {
        initComponents(user);
        setSize(1300, 800);
        setLocation(330,120);
        groupSelect.addItem("学习");
    }

    private void summitActionPerformed(ActionEvent e,User user) {
        String title = titleField.getText();
        String text = textArea.getText();
        String access = (String) this.accessSelect.getSelectedItem();
        Note note = new Note(null, title, user.getUserName(), text, access, 0, "0");
        new NoteDaoImpl().addNote(note);
    }

    private void initComponents(User user) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titleField = new JTextField();
        title = new JLabel();
        scrollPane = new JScrollPane();
        textArea = new JTextArea();
        summit = new JButton();
        accessSelect = new JComboBox<>();
        access = new JLabel();
        group = new JLabel();
        groupSelect = new JComboBox<>();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        Container contentPane = getContentPane();

        //---- title ----
        title.setText("\u6807\u9898");
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 3f));

        //======== scrollPane ========
        {
            scrollPane.setViewportView(textArea);
        }

        //---- summit ----
        summit.setText("\u63d0\u4ea4");
        summit.addActionListener(e -> summitActionPerformed(e,user));

        //---- accessSelect ----
        accessSelect.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u516c\u5f00",
            "\u79c1\u6709"
        }));

        //---- access ----
        access.setText("\u6743\u9650");

        //---- group ----
        group.setText("\u5206\u7ec4");

        //---- groupSelect ----
        groupSelect.setModel(new DefaultComboBoxModel<>(new String[] {
            "null"
        }));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(access, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(accessSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(107, 107, 107)
                            .addComponent(group, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(groupSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44)
                            .addComponent(summit))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(title)
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 694, GroupLayout.PREFERRED_SIZE)
                                .addComponent(titleField, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(55, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(title, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(titleField, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(access, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(summit)
                        .addComponent(groupSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(group)
                        .addComponent(accessSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(359, 359, 359))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField titleField;
    private JLabel title;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JButton summit;
    private JComboBox<String> accessSelect;
    private JLabel access;
    private JLabel group;
    private JComboBox<String> groupSelect;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
