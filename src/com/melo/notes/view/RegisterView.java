/*
 * Created by JFormDesigner on Sat Mar 27 15:28:47 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.service.impl.RegisterServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @description 注册界面
 */
public class RegisterView extends JFrame {



    /**
     * 注册成功状态码
     */
    private final String SUCCESS="操作成功";

    /**
     * 创建相关操作类对象
     */
    RegisterServiceImpl registerService = (RegisterServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.RegisterService);
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);
    public RegisterView() {
        initComponents();
        setSize(1300, 800);
        setLocation(330,120);
    }

    private void registerActionPerformed(ActionEvent e) {
        /**
         * 获取文本框内容
         */
        String userName = userNameText.getText();
        String firstPass = String.valueOf(passwordField1.getPassword());
        String secondPass = String.valueOf(passwordField2.getPassword());

        String message = registerService.isValid(userName, firstPass, secondPass);
        JOptionPane.showMessageDialog(null,message);
        if(message.equals(SUCCESS)){
            registerService.addUser(userName,secondPass);
            this.dispose();
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        userName = new JLabel();
        password1 = new JLabel();
        userNameText = new JTextField();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        password2 = new JLabel();
        register = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(950, 650));
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setTitle("\u6ce8\u518c");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        Container contentPane = getContentPane();

        //---- userName ----
        userName.setText("\u7528\u6237\u540d");
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setAlignmentY(0.0F);
        userName.setMinimumSize(new Dimension(1919, 1035));
        userName.setMaximumSize(new Dimension(1919, 1035));
        userName.setBackground(new Color(102, 255, 255));
        userName.setFont(new Font("??????", Font.PLAIN, 18));

        //---- password1 ----
        password1.setText("\u5bc6\u7801");
        password1.setHorizontalAlignment(SwingConstants.CENTER);
        password1.setFont(new Font("??????", Font.PLAIN, 18));

        //---- password2 ----
        password2.setText("\u518d\u6b21\u8f93\u5165");
        password2.setHorizontalAlignment(SwingConstants.CENTER);
        password2.setFont(new Font("??????", Font.PLAIN, 18));

        //---- register ----
        register.setText("\u6ce8\u518c");
        register.addActionListener(e -> registerActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(72, 72, 72)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userName, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(password1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(password2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userNameText, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(217, 217, 217)
                            .addComponent(register, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
                    .addGap(284, 284, 284))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(116, 116, 116)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(userName, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                        .addComponent(userNameText, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addComponent(password1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addComponent(password2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addComponent(register, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19))
        );
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel userName;
    private JLabel password1;
    private JTextField userNameText;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel password2;
    private JButton register;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
