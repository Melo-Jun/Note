/*
 * Created by JFormDesigner on Fri Mar 26 22:14:56 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.dao.LoginDao;
import com.melo.notes.entity.User;
import com.sun.xml.internal.ws.util.StringUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 */
public class LoginView extends JFrame {
    /*public LoginView() {
        initComponents();
    }

    private void loginActionPerformed(ActionEvent e) {
        //获取文本框内容
        String userName = userNameText.getText();
        String password = String.valueOf(passwordField.getPassword());
        //获取下拉权限值
        String permission = (String) this.permission.getSelectedItem();
        //判断信息是否都有填完整
        if (userName==null) {
            JOptionPane.showMessageDialog(null, "用户名不能为空");
            //重新输入
            return;
        }
        if (password==null) {
            JOptionPane.showMessageDialog(null, "密码不能为空");
            //重新输入
            return;
        }
        //验证用户名和密码
        if (LoginDao.login(new User(userName,password))) {
            //验证权限
            if (LoginDao.permission(permission, new User(userName,password))) {
                JOptionPane.showMessageDialog(null, "登录成功");
            } else {
                JOptionPane.showMessageDialog(null, "登录失败");
            }
        } else {
            JOptionPane.showMessageDialog(null, "登录失败");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        userNameText = new JTextField();
        userName = new JLabel();
        password = new JLabel();
        login = new JButton();
        permission = new JComboBox<>();
        passwordField = new JPasswordField();

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
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(permission, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(login))
                        .addComponent(userNameText, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                        .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
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
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(53, 53, 53)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(login)
                        .addComponent(permission, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(179, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField userNameText;
    private JLabel userName;
    private JLabel password;
    private JButton login;
    private JComboBox<String> permission;
    private JPasswordField passwordField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables*/
}
