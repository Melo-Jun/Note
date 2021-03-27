/*
 * Created by JFormDesigner on Fri Mar 26 22:14:56 CST 2021
 */

package com.melo.notes.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author 1
 */
public class LoginView extends JFrame {
    public LoginView() {
        initComponents();
    }

    private void loginActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"登录成功");

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        userNameText = new JTextField();
        userName = new JLabel();
        password = new JLabel();
        passwordText = new JTextField();
        login = new JButton();
        permission = new JComboBox<>();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //---- userName ----
        userName.setText("\u7528\u6237\u540d");

        //---- password ----
        password.setText("\u5bc6\u7801");

        //---- login ----
        login.setText("\u767b\u5f55");
        login.addActionListener(e -> {
			loginActionPerformed(e);
			loginActionPerformed(e);
		});

        //---- permission ----
        permission.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7528\u6237",
            "\u7ba1\u7406\u5458"
        }));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(userName, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                        .addComponent(password, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(permission, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(login))
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(userNameText, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                            .addComponent(passwordText, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)))
                    .addContainerGap(182, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(155, 155, 155)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userNameText, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(53, 53, 53)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(login)
                        .addComponent(permission, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(180, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField userNameText;
    private JLabel userName;
    private JLabel password;
    private JTextField passwordText;
    private JButton login;
    private JComboBox<String> permission;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
