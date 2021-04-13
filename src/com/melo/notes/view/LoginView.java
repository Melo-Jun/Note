/*
 * Created by JFormDesigner on Sat Mar 27 10:42:14 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.controller.LoginController;
import com.melo.notes.entity.User;
import com.melo.notes.service.impl.LoginServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description ��¼����
 * @date 2021-4
 */
public class LoginView extends JFrame {
    /**
     * ��ز�����
     */
    LoginServiceImpl loginViewService = (LoginServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.LoginService);

    /**
     * ����Ա�����
     */
    private final String ADMIN="����Ա";
    /**
     * ��¼�ɹ�״̬��
     */
    private final String USERVIEW="��¼�ɹ�";
    private final String ADMINVIEW="��ӭ����Ա";
    /**
     * �洢��¼�������û�
     */
    public static User USER=new User();

    public LoginView() {
        initComponents();
        setVisible(true);
        setSize(250, 400);
        setLocation(800,300);
    }

    public String getUserName(){
        return userNameText.getText();
    }
    public String getPass() {
        return String.valueOf(passwordField.getPassword());
    }
    public String getAccess() {
        return (String) this.access.getSelectedItem();
    }

    /**
     * ��¼��ť�¼�
     * @param e
     */
    private void loginActionPerformed(ActionEvent e) {
        new LoginController().login(this);


        /**
         * ��ȡ�ı������ݺ�����Ȩ��ֵ
         */
        /*String userName = userNameText.getText();
        String password = String.valueOf(passwordField.getPassword());
        String access = (String) this.access.getSelectedItem();
        String message = loginViewService.login(userName, password, access);
        JOptionPane.showMessageDialog(null,message);
        if(message.equals(USERVIEW)) {
            USER = new User(userName, password);
            /**
             * ���ٵ�ǰ���ڲ��洢user����id����ת���û�����
             */
            /*loginViewService.setId(USER);
            this.dispose();
            new UserView(USER).setVisible(true);
        }
        if(message.equals(ADMINVIEW)){
            new AdminView();
        }*/
    }
    /**
     * ע�ᰴť�¼�
     * @param e
     */
    private void registerActionPerformed(ActionEvent e) {
        new RegisterView().setVisible(true);
    }


    /**
     * ��ʼ������
     */
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
        setMinimumSize(new Dimension(350, 150));
        setResizable(false);
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
                    .addGap(44, 44, 44)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(userName)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(password, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                        .addComponent(access, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(register)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(login))
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userNameText, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                    .addGap(49, 49, 49))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(104, 104, 104)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(userName, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(userNameText, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
                    .addGap(25, 25, 25)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(register)
                                .addComponent(login)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(access, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(42, 42, 42))
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
    public JButton login;
    public JButton register;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
