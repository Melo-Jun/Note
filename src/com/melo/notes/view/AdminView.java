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

    /**
     * 发布公告
     * @param e
     */
    private void announceActionPerformed(ActionEvent e) {
        new AddAnnouncementView();
    }

    /**
     * 查看用户信息
     * @param e
     */
    private void userInformationActionPerformed(ActionEvent e) {
        new UserTableView();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        userInformation = new JButton();
        announce = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        Container contentPane = getContentPane();

        //---- userInformation ----
        userInformation.setText("\u67e5\u770b\u7528\u6237\u4fe1\u606f");
        userInformation.addActionListener(e -> userInformationActionPerformed(e));

        //---- announce ----
        announce.setText("\u53d1\u5e03\u516c\u544a");
        announce.addActionListener(e -> announceActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(149, 149, 149)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(announce, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(userInformation)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap(167, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(userInformation, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                    .addGap(42, 42, 42)
                    .addComponent(announce, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(65, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton userInformation;
    private JButton announce;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
