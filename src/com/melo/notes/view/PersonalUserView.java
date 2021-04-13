/*
 * Created by JFormDesigner on Thu Apr 08 18:20:57 CST 2021
 */

package com.melo.notes.view;

import java.awt.event.*;

import com.melo.notes.controller.PersonalUserController;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description 设置个人信息界面
 * @date 2021-4
 */
public class PersonalUserView extends JFrame {

    public PersonalUserView() {
        initComponents();
        setVisible(true);
        nameText.setText(LoginView.USER.getUserName());
        setSize(350,400);
    }

    public String getUserName(){
        return nameText.getText();
    }
    public String getOldPass(){
        return String.valueOf(oldPassword.getPassword());
    }
    public String getFirstPass(){
        return String.valueOf(newPassword.getPassword());
    }
    public String getSecondPass(){
        return  String.valueOf(newPassword2.getPassword());
    }

    private void submitActionPerformed(ActionEvent e) {
        new PersonalUserController().updatePersonalUser(this);
       /* userName = nameText.getText();
        String oldPass=String.valueOf(oldPassword.getPassword());
        String firstPass = String.valueOf(newPassword.getPassword());
        String secondPass = String.valueOf(newPassword2.getPassword());
        String message= personalUserService.isValid(userName,firstPass,secondPass);
        if(message.equals(SUCCESS)){
            if(personalUserService.judgePass(userName,oldPass)){
                personalUserService.updateUser(userName,secondPass);
                JOptionPane.showMessageDialog(null,"修改成功,需要手动重启");
                System.exit(-1);
            }else {
                JOptionPane.showMessageDialog(null,"原密码输入有误");
            }
        }else {
            JOptionPane.showMessageDialog(null,message);
        }*/
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
        setTitle("\u8bbe\u7f6e\u4e2a\u4eba\u4fe1\u606f");
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d");
        label1.setFont(new Font("Microsoft YaHei UI", label1.getFont().getStyle(), label1.getFont().getSize() + 4));

        //---- oldPass ----
        oldPass.setText("\u539f\u6765\u7684\u5bc6\u7801");
        oldPass.setFont(new Font("Microsoft YaHei UI", oldPass.getFont().getStyle() & ~Font.BOLD, oldPass.getFont().getSize() + 4));

        //---- newPass ----
        newPass.setText("\u8bf7\u8f93\u5165\u65b0\u5bc6\u7801");
        newPass.setFont(new Font("Microsoft YaHei UI", newPass.getFont().getStyle() & ~Font.BOLD, newPass.getFont().getSize() + 4));

        //---- submit ----
        submit.setText("\u786e\u8ba4\u4fee\u6539");
        submit.setFont(new Font("Microsoft YaHei UI", submit.getFont().getStyle() & ~Font.BOLD, submit.getFont().getSize() + 4));
        submit.addActionListener(e -> submitActionPerformed(e));

        //---- newPass2 ----
        newPass2.setText("\u518d\u6b21\u8f93\u5165");
        newPass2.setFont(new Font("Microsoft YaHei UI", newPass2.getFont().getStyle() & ~Font.BOLD, newPass2.getFont().getSize() + 4));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(newPass)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                            .addComponent(newPassword, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(oldPass, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addGap(32, 32, 32)))
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(oldPassword, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                .addComponent(nameText, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(newPass2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                            .addComponent(newPassword2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
                    .addGap(48, 48, 48))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(95, 95, 95)
                    .addComponent(submit)
                    .addContainerGap(115, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGap(13, 13, 13)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(oldPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(oldPass, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(newPass, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(newPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(newPassword2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(newPass2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                    .addComponent(submit)
                    .addGap(47, 47, 47))
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
