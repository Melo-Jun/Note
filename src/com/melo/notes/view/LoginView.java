/*
 * Created by JFormDesigner on Sat Mar 27 10:42:14 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.dao.impl.LoginDaoImpl;
import com.melo.notes.entity.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @description ��¼����
 */
public class LoginView extends JFrame {

    private static String ADMIN="����Ա";

    public LoginView() {
        initComponents();
        setSize(1300, 800);
        setLocation(330,120);
    }

    private void loginActionPerformed(ActionEvent e) {
        //��ȡ�ı�������
        String userName = userNameText.getText();
        String password = String.valueOf(passwordField.getPassword());
        System.out.println(userName);
        System.out.println(password);
        //��ȡ����Ȩ��ֵ
        String access = (String) this.access.getSelectedItem();
        //�ж���Ϣ�Ƿ���������
        if (userName==null) {
            JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
            //��������
            return;
        }
        if (password==null) {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
            //��������
            return;
        }
        if(access==ADMIN){

        }
        else{
            /**
             * ��֤�û���������
             */
            User user = new User(userName, password);
            if (new LoginDaoImpl().login(user)) {
                JOptionPane.showMessageDialog(null, "��¼�ɹ�");
                /**
                 * ���ٵ�ǰ���ڣ���ת���û�����
                 */
                this.dispose();
                new UserView(user).setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "��¼ʧ��");
            }
        }

    }

    private void registerActionPerformed(ActionEvent e) {
        new RegisterView().setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        userName = new JLabel();
        userNameText = new JTextField();
        password = new JLabel();
        passwordField = new JPasswordField();
        access = new JComboBox<>();
        login = new JButton();
        register = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        setTitle("\u767b\u5f55");
        setLocationByPlatform(true);
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        Container contentPane = getContentPane();

        //---- userName ----
        userName.setText("\u7528\u6237\u540d");
        userName.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- userNameText ----
        userNameText.setHorizontalAlignment(SwingConstants.LEFT);
        userNameText.setMaximumSize(new Dimension(20, 50));

        //---- password ----
        password.setText("\u5bc6\u7801");
        password.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- access ----
        access.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7528\u6237",
            "\u7ba1\u7406\u5458"
        }));
        access.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- login ----
        login.setText("\u767b\u5f55");
        login.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        login.addActionListener(e -> loginActionPerformed(e));

        //---- register ----
        register.setText("\u6ce8\u518c");
        register.setBackground(new Color(238, 238, 238));
        register.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        register.addActionListener(e -> registerActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(234, 234, 234)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(register)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(access, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(login))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(73, 73, 73)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(password, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(userName)
                                    .addGap(46, 46, 46)
                                    .addComponent(userNameText, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))))
                    .addGap(129, 185, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(182, 182, 182)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userName, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userNameText, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                    .addGap(38, 38, 38)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(login)
                        .addComponent(access, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(38, 38, 38)
                    .addComponent(register)
                    .addContainerGap())
        );
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel userName;
    private JTextField userNameText;
    private JLabel password;
    private JPasswordField passwordField;
    private JComboBox<String> access;
    private JButton login;
    private JButton register;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
