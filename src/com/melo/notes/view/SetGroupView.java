/*
 * Created by JFormDesigner on Mon Apr 05 17:19:53 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description 设置笔记分组界面
 * @date 2021-4
 */
public class SetGroupView extends JFrame {

    /**
     * 创建相关操作对象
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    public SetGroupView() {
        initComponents();
        setVisible(true);
        /*
          初始化下拉框
         */
        HashMap<Object, Object> folder = folderGroupService.showFolderName();
        Set<Map.Entry<Object, Object>> folderSet =  folder.entrySet();
        for(Map.Entry tempFolder:folderSet) {
            String folderId = tempFolder.getKey().toString();
            String folerName=tempFolder.getValue().toString();
            selectedLocatedFolder.addItem(folderId+"--"+folerName);
        }
    }

    /**
     * 确认修改按钮
     * @param e
     */
    private void submitActionPerformed(ActionEvent e) {
        String locatedFolder = (String) selectedLocatedFolder.getSelectedItem();
        if (folderGroupService.setGroup(FolderView.selectedName, locatedFolder,FolderView.selectedId) != 0) {
            JOptionPane.showMessageDialog(null, Status.SUCCESS.getMessage());
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, Status.FAILED.getMessage());
            this.dispose();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label3 = new JLabel();
        selectedLocatedFolder = new JComboBox();
        submit = new JButton();
        label1 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        Container contentPane = getContentPane();

        //---- label3 ----
        label3.setText("\u76ee\u6807\u77e5\u8bc6\u5e93");

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
                            .addGap(59, 59, 59)
                            .addComponent(label3)
                            .addGap(18, 18, 18)
                            .addComponent(selectedLocatedFolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(107, 107, 107)
                            .addComponent(submit))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(72, 72, 72)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(95, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(selectedLocatedFolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(submit)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label3;
    private JComboBox selectedLocatedFolder;
    private JButton submit;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
