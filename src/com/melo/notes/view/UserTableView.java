/*
 * Created by JFormDesigner on Sun Apr 11 16:01:47 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.entity.User;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.constant.TypeName;
import com.melo.notes.service.impl.UserTableServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author Jun
 * @program Note
 * @description 所有用户信息表格界面
 * @date 2021-4
 */
public class UserTableView extends JFrame {

    /**
     * 创建相关操作类
     */
    UserTableServiceImpl userTableService=(UserTableServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.UserTableService);

    private DefaultTableModel model= null;
    private JTable table=null;

    private String userId=null;

    public UserTableView() {
        initComponents();
        setVisible(true);
        fillTable(new User());
    }

    /**
     * 表格点击监听
     * @param e
     */
    private void table1MouseClicked(MouseEvent e) {
        int row=table.getSelectedRow();
        if(row!=-1) {
             userId=(String) model.getValueAt(row, 0);
        }
    }

    /**
     * 拉黑
     * @param e
     */
    private void blockActionPerformed(ActionEvent e) {
        if(userTableService.updateValidity(TypeName.NOT_VALID.getMessage(), userId)!=0) {
            JOptionPane.showMessageDialog(null, Status.SUCCESS.getMessage());
        }else {
            JOptionPane.showMessageDialog(null,Status.FAILED.getMessage());
        }
    }

    /**
     * 取消拉黑
     * @param e
     */
    private void withdrawBlockActionPerformed(ActionEvent e) {
        if(userTableService.updateValidity(TypeName.VALID.getMessage(), userId)!=0) {
            JOptionPane.showMessageDialog(null, Status.SUCCESS.getMessage());
        }else {
            JOptionPane.showMessageDialog(null,Status.FAILED.getMessage());
        }
    }

    /**
     * 查看用户笔记信息
     * @param e
     */
    private void noteInformationActionPerformed(ActionEvent e) {
       new UserNoteView(userId);
    }

    /**
     * 刷新
     * @param e
     */
    private void backActionPerformed(ActionEvent e) {
        fillTable(new User());
    }

    /**
     * 填充表格
     * @param user 相关对象
     */
    private void fillTable(User user){
        model= (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        LinkedList<User> users = userTableService.showUserAll(user);
        for(User tempUser:users) {
            model.addRow(userTableService.fillTable(tempUser));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table = new JTable();
        block = new JButton();
        withdrawBlock = new JButton();
        noteInformation = new JButton();
        back = new JButton();

        //======== this ========
        setTitle("\u67e5\u770b\u7b14\u8bb0");
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {
            scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //---- table ----
            table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "id", "userName", "password", "validity"
                }
            ));
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            table.setFillsViewportHeight(true);
            table.setAlignmentY(1.0F);
            table.setAutoCreateRowSorter(true);
            table.setPreferredScrollableViewportSize(new Dimension(450, 500));
            table.setShowVerticalLines(false);
            table.setShowHorizontalLines(false);
            table.setRowHeight(20);
            table.setAlignmentX(1.0F);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table);
        }

        //---- block ----
        block.setText("\u62c9\u9ed1");
        block.addActionListener(e -> blockActionPerformed(e));

        //---- withdrawBlock ----
        withdrawBlock.setText("\u53d6\u6d88\u62c9\u9ed1");
        withdrawBlock.addActionListener(e -> withdrawBlockActionPerformed(e));

        //---- noteInformation ----
        noteInformation.setText("\u67e5\u770b\u7528\u6237\u7b14\u8bb0\u4fe1\u606f");
        noteInformation.addActionListener(e -> noteInformationActionPerformed(e));

        //---- back ----
        back.setText("\u5237\u65b0");
        back.addActionListener(e -> backActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(166, 166, 166)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(withdrawBlock, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(block, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                    .addComponent(noteInformation))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(80, 80, 80)
                                    .addComponent(back)
                                    .addGap(0, 114, Short.MAX_VALUE)))
                            .addGap(62, 62, 62))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(noteInformation)
                        .addComponent(block))
                    .addGap(18, 18, 18)
                    .addComponent(withdrawBlock)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(back)
                    .addContainerGap(48, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JButton block;
    private JButton withdrawBlock;
    private JButton noteInformation;
    private JButton back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
