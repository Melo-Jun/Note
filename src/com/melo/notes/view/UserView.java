/*
 * Created by JFormDesigner on Sat Mar 27 21:13:31 CST 2021
 */

package com.melo.notes.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author 1
 */
public class UserView extends JFrame {
    public UserView() {
        initComponents();
    }

    private void optionActionPerformed(ActionEvent e) {
        if(option.getSelectedItem()=="新增笔记"){
            new LoginView().setVisible(true);
        }
    }

    private void optionMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        option = new JComboBox<>();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        Container contentPane = getContentPane();

        //---- option ----
        option.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u65b0\u589e\u7b14\u8bb0"
        }));
        option.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 18));
        option.addActionListener(e -> optionActionPerformed(e));
        option.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                optionMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(option, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 841, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(option, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(586, Short.MAX_VALUE))
        );
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JComboBox<String> option;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
