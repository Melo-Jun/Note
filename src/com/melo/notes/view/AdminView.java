/*
 * Created by JFormDesigner on Thu Apr 08 18:13:20 CST 2021
 */

package com.melo.notes.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description 管理员界面
 * @date 2021-4
 */
public class AdminView extends JFrame {
    public AdminView() {
        initComponents();
        setVisible(true);
    }

    private void userInformationActionPerformed(ActionEvent e) {
        new UserTableView();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        userInformation = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        Container contentPane = getContentPane();

        //---- userInformation ----
        userInformation.setText("\u67e5\u770b\u7528\u6237\u4fe1\u606f");
        userInformation.addActionListener(e -> userInformationActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(150, 150, 150)
                    .addComponent(userInformation)
                    .addContainerGap(166, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(userInformation)
                    .addContainerGap(184, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton userInformation;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
