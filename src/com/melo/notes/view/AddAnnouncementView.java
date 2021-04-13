/*
 * Created by JFormDesigner on Tue Apr 13 20:46:56 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.AdminServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description 公告界面
 * @date 2021-4-13 20:50
 */
public class AddAnnouncementView extends JFrame {

    /**
     * 相关操作类
     */
    AdminServiceImpl adminService=(AdminServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.AdminServiceImpl);

    public AddAnnouncementView() {
        initComponents();
        setVisible(true);
    }


    /**
     * 确认发布公告按钮
     * @param e
     */
    private void submitActionPerformed(ActionEvent e) {
        String title = titleText.getText();
        String text = textText.getText();
        if(adminService.addAnnouncement(title,text)){
            JOptionPane.showMessageDialog(null, Status.SUCCESS.getMessage());
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(null,Status.FAILED.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titleText = new JTextField();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        textText = new JTextArea();
        label2 = new JLabel();
        submit = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        setTitle("\u53d1\u5e03\u516c\u544a");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u6807\u9898");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textText);
        }

        //---- label2 ----
        label2.setText("\u5185\u5bb9");

        //---- submit ----
        submit.setText("\u786e\u8ba4");
        submit.addActionListener(e -> submitActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(submit)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1)
                                .addComponent(label2))
                            .addGap(36, 36, 36)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                                .addComponent(titleText, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))))
                    .addContainerGap(91, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(titleText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(submit)
                    .addContainerGap(14, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField titleText;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea textText;
    private JLabel label2;
    private JButton submit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
