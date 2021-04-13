/*
 * Created by JFormDesigner on Mon Apr 05 15:57:57 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description 增加笔记分组界面
 * @date 2021-4
 */
public class AddFolderView extends JFrame {

    /**
     * 创建相关操作对象
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    public AddFolderView() {
        initComponents();
        setVisible(true);
    }

    private void submitActionPerformed(ActionEvent e) {
        String name = nameText.getText();
        String access = (String)selectedAccess.getSelectedItem();
        if(folderGroupService.addFolder(name,access,LoginView.USER.getId())&&!name.isEmpty()){
            JOptionPane.showMessageDialog(null,"增加成功");
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(null,"增加失败");
        }
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        selectedAccess = new JComboBox<>();
        nameText = new JTextField();
        name = new JLabel();
        access = new JLabel();
        submit = new JButton();
        label1 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        Container contentPane = getContentPane();

        //---- selectedAccess ----
        selectedAccess.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u516c\u5f00",
            "\u79c1\u6709"
        }));

        //---- name ----
        name.setText("\u540d\u79f0");

        //---- access ----
        access.setText("\u6743\u9650");

        //---- submit ----
        submit.setText("\u786e\u5b9a");
        submit.addActionListener(e -> submitActionPerformed(e));

        //---- label1 ----
        label1.setText("\u64cd\u4f5c\u5b8c\u6bd5\u540e\u8bb0\u5f97\u5237\u65b0");
        label1.setForeground(SystemColor.activeCaption);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(name)
                                    .addGap(18, 18, 18)
                                    .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(access)
                                    .addGap(18, 18, 18)
                                    .addComponent(selectedAccess, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                    .addGap(72, 72, 72)
                                    .addComponent(submit))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 74, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(name)
                        .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(access, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submit)
                        .addComponent(selectedAccess, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(55, 55, 55))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JComboBox<String> selectedAccess;
    private JTextField nameText;
    private JLabel name;
    private JLabel access;
    private JButton submit;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
