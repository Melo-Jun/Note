/*
 * Created by JFormDesigner on Thu Apr 08 18:20:57 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.service.impl.RegisterServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 */
public class PersonalUserView extends JFrame {

    /**
     * 注册成功状态码
     */
    private final String SUCCESS="操作成功";

    /**
     * 创建相关操作类对象
     */
    RegisterServiceImpl registerService = (RegisterServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.RegisterService);
    public PersonalUserView() {
        initComponents();
        setVisible(true);
        nameText.setText(LoginView.USER.getUserName());
        String userName = nameText.getText();
        String firstPass = String.valueOf(newPassword.getPassword());
        String secondPass = String.valueOf(newPassword2.getPassword());
        String message= registerService.isValid(userName,firstPass,secondPass);
        JOptionPane.showMessageDialog(null,message);
        if(message.equals(SUCCESS)){
            this.dispose();
        }
        setSize(300,350);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        oldPass = new JLabel();
        nameText = new JTextField();
        newPass = new JLabel();
        oldPassword = new JPasswordField();
        newPassword = new JPasswordField();
        submit = new JButton();
        newPass2 = new JLabel();
        newPassword2 = new JPasswordField();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\u79f0");

        //---- oldPass ----
        oldPass.setText("\u539f\u6765\u7684\u5bc6\u7801");

        //---- newPass ----
        newPass.setText("\u66f4\u65b0\u7684\u5bc6\u7801");

        //---- submit ----
        submit.setText("\u786e\u8ba4\u4fee\u6539");

        //---- newPass2 ----
        newPass2.setText("\u518d\u6b21\u8f93\u5165");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(97, 97, 97)
                            .addComponent(submit))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(newPass)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(newPassword, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label1)
                                        .addComponent(oldPass, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                                    .addGap(21, 21, 21)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(oldPassword, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(newPass2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(newPassword2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(oldPass)
                        .addComponent(oldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(newPass)
                        .addComponent(newPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(newPass2)
                        .addComponent(newPassword2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                    .addComponent(submit)
                    .addGap(59, 59, 59))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel oldPass;
    private JTextField nameText;
    private JLabel newPass;
    private JPasswordField oldPassword;
    private JPasswordField newPassword;
    private JButton submit;
    private JLabel newPass2;
    private JPasswordField newPassword2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
