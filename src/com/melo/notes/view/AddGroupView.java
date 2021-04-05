/*
 * Created by JFormDesigner on Mon Apr 05 16:27:54 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 */
public class AddGroupView extends JFrame {

    /**
     * 创建相关操作对象
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    public AddGroupView() {
        initComponents();
        /**
         * 初始化下拉框
         */
        HashMap<Object, Object> folderName = folderGroupService.showFolderName(LoginView.USER);
        Collection<Object> values = folderName.values();
        for(Object temp:values){
            selectedLocatedFolder.addItem(temp);
        }
    }

    private void submitActionPerformed(ActionEvent e) {
        String groupName = nameText.getText();
        Object locatedFolder= selectedLocatedFolder.getSelectedItem();
        if(folderGroupService.addGroup(groupName,locatedFolder.toString())!=0&&!groupName.isEmpty()){
            JOptionPane.showMessageDialog(null,"增加成功");
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(null,"增加失败");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label3 = new JLabel();
        selectedLocatedFolder = new JComboBox();
        submit = new JButton();
        name = new JLabel();
        nameText = new JTextField();
        label1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label3 ----
        label3.setText("\u6240\u5728\u77e5\u8bc6\u5e93");

        //---- submit ----
        submit.setText("\u786e\u5b9a");
        submit.addActionListener(e -> submitActionPerformed(e));

        //---- name ----
        name.setText("\u540d\u79f0");

        //---- label1 ----
        label1.setText("\u64cd\u4f5c\u5b8c\u6bd5\u540e\u8bb0\u5f97\u5237\u65b0");
        label1.setForeground(SystemColor.activeCaption);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(name)
                            .addGap(18, 18, 18)
                            .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(submit)
                                .addComponent(label3))
                            .addGap(18, 18, 18)
                            .addComponent(selectedLocatedFolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(61, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 127, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                    .addGap(85, 85, 85))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(12, Short.MAX_VALUE)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(name))
                        .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectedLocatedFolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3))
                    .addGap(18, 18, 18)
                    .addComponent(submit)
                    .addGap(24, 24, 24))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label3;
    private JComboBox selectedLocatedFolder;
    private JButton submit;
    private JLabel name;
    private JTextField nameText;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
