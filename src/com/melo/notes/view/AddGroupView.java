/*
 * Created by JFormDesigner on Mon Apr 05 16:27:54 CST 2021
 */

package com.melo.notes.view;

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
 * @description 增加笔记界面
 * @date 2021-4
 */
public class AddGroupView extends JFrame {

    /**
     * 创建相关操作对象
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    public AddGroupView() {
        initComponents();
        setVisible(true);
//        /*
//          初始化下拉框
//         */
//        HashMap<Object, Object> folder = folderGroupService.showFolderName();
//        /*
//        遍历获取folderId和folderName
//        */
//        Set<Map.Entry<Object, Object>> folderSet =  folder.entrySet();
//        for(Map.Entry tempFolder:folderSet) {
//            String folderId = tempFolder.getKey().toString();
//            String folerName=tempFolder.getValue().toString();
//            selectedLocatedFolder.addItem(folderId+"--"+folerName);
//        }
    }

    /**
     * 确认增加笔记分组
     * @param e
     */
    private void submitActionPerformed(ActionEvent e) {
        String groupName = nameText.getText();
//        String locatedFolder= selectedLocatedFolder.getSelectedItem().toString();
//        //截断获取知识库id
//        int location = locatedFolder.indexOf("-");
//        String folderId = locatedFolder.substring(0, location);
        if(folderGroupService.addGroup(groupName,FolderView.selectedId)){
            JOptionPane.showMessageDialog(null,"增加成功");
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(null,"增加失败");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        submit = new JButton();
        name = new JLabel();
        nameText = new JTextField();
        label1 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        Container contentPane = getContentPane();

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
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(submit)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(name)
                    .addGap(18, 18, 18)
                    .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(61, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 127, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                    .addGap(85, 85, 85))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(name))
                        .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addGap(74, 74, 74)
                    .addComponent(submit)
                    .addGap(24, 24, 24))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton submit;
    private JLabel name;
    private JTextField nameText;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
