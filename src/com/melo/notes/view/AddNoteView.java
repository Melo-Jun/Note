/*
 * Created by JFormDesigner on Sun Mar 28 16:42:06 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Group;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 */
public class AddNoteView extends JFrame {

    /**
     * 创建相关操作类对象
     */
    NoteDaoImpl noteDao = (NoteDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.NoteDao);
    FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

    public AddNoteView(User user) {
        this.setTitle("记录daily");
        initComponents(user);
        setSize(1300, 800);
        setLocation(330,120);
    }

    /**
     * 提交按钮事件
     * @param e
     */
    private void summitActionPerformed(ActionEvent e) {
        Group group = new Group();
        group.setGroupName(TreeView.selectedName);
        TreeView.selectedId= folderGroupService.getId(group);
        String title = titleField.getText();
        String text = textArea.getText();
        String access = (String) this.accessSelect.getSelectedItem();
        Note note = new Note( title, LoginView.USER.getId(), text, access ,"0",TreeView.selectedId );
        if(noteDao.addNote(note)){
            JOptionPane.showMessageDialog(null,"增加笔记成功");
            this.dispose();
        }
    }

    /**
     * 设置笔记分组
     * @param e
     */
    private void selectGroupActionPerformed(ActionEvent e) {
        new TreeView();
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
        selectGroup = new JButton();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setTitle("\u8bb0\u5f55daily");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- title ----
        title.setText("\u6807\u9898");
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 3f));

        //======== scrollPane ========
        {
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

        //---- selectGroup ----
        selectGroup.setText("\u8bbe\u7f6e\u5206\u7ec4");
        selectGroup.addActionListener(e -> selectGroupActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
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
                            .addGap(107, 107, 107)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(selectGroup)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(132, 132, 132)
                                    .addComponent(summit)))))
                    .addContainerGap(55, Short.MAX_VALUE))
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
                        .addComponent(summit)
                        .addComponent(accessSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(access, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(selectGroup)
                    .addContainerGap(419, Short.MAX_VALUE))
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
    private JButton selectGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
