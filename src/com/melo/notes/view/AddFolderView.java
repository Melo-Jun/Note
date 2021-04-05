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
 * @author 1
 */
public class AddFolderView extends JFrame {

    /**
     * ������ز�������
     */
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    /*public static String name=" ";
    public static String access=" ";
    public static String type=" ";*/

    public AddFolderView() {
        initComponents();
    }

    private void submitActionPerformed(ActionEvent e) {
        String name = nameText.getText();
        String access = (String)selectedAccess.getSelectedItem();
        if(folderGroupService.addFolder(name,access)!=0&&!name.isEmpty()){
            JOptionPane.showMessageDialog(null,"���ӳɹ�");
            this.dispose();
            new FolderView(LoginView.USER);
        }else {
            JOptionPane.showMessageDialog(null,"����ʧ��");
        }
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        selectedAccess = new JComboBox<>();
        nameText = new JTextField();
        name = new JLabel();
        access = new JLabel();
        submit = new JButton();

        //======== this ========
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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
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
                            .addComponent(submit)))
                    .addGap(0, 86, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
