/*
 * Created by JFormDesigner on Sun Mar 28 16:42:06 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.entity.User;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description 增加笔记界面
 * @date 2021-4
 */
public class AddNoteView extends JFrame {

    /**
     * 创建相关操作类对象
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    public AddNoteView(User user) {
        this.setTitle("记录daily");
        initComponents(user);
        setVisible(true);
        setSize(550, 650);
        setLocation(600,260);
    }

    /**
     * 提交按钮事件
     * @param e
     */
    private void summitActionPerformed(ActionEvent e) {
        String groupId = FolderView.selectedId;
        String title = titleField.getText();
        String text = textArea.getText();
        String access = (String) this.accessSelect.getSelectedItem();
        if(folderGroupService.addNote(title,text,access,groupId)){
            JOptionPane.showMessageDialog(null, Status.SUCCESS.getMessage());
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(null,Status.FAILED.getMessage());
        }
    }







    /**
     * 初始化页面
     * @param user
     */
    private void initComponents(User user) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titleField = new JTextField();
        title = new JLabel();
        scrollPane = new JScrollPane();
        textArea = new JTextArea();
        summit = new JButton();
        accessSelect = new JComboBox<>();
        access = new JLabel();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setTitle("\u8bb0\u5f55daily");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- titleField ----
        titleField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- title ----
        title.setText("\u6807\u9898");
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 3f));

        //======== scrollPane ========
        {
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //---- textArea ----
            textArea.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, textArea.getFont().getSize() + 6));
            textArea.setRows(5);
            textArea.setTabSize(0);
            textArea.setLineWrap(true);
            scrollPane.setViewportView(textArea);
        }

        //---- summit ----
        summit.setText("\u63d0\u4ea4");
        summit.addActionListener(e -> summitActionPerformed(e));

        //---- accessSelect ----
        accessSelect.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u516c\u5f00",
            "\u79c1\u6709"
        }));

        //---- access ----
        access.setText("\u6743\u9650");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(title)
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(titleField, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 694, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(access, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(accessSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(summit)))
                    .addGap(55, 55, 55))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(title, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(titleField, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(accessSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(access, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(summit))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField titleField;
    private JLabel title;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JButton summit;
    private JComboBox<String> accessSelect;
    private JLabel access;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
