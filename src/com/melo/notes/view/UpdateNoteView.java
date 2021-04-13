/*
 * Created by JFormDesigner on Thu Apr 08 14:58:20 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.entity.Group;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.service.impl.NoteServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

/**
 * @author Jun
 * @program Note
 * @description 设置个人信息界面
 * @date 2021-4
 */
public class UpdateNoteView extends JFrame {

        /**
         * 创建相关操作类对象
         */
        NoteServiceImpl noteService=(NoteServiceImpl)BeanFactory.getBean(BeanFactory.ServiceType.NoteService);
        FolderGroupServiceImpl folderGroupService=(FolderGroupServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.FolderGroupService);

        Note note=null;

        public UpdateNoteView(User user) {
            initComponents(user);
            setVisible(true);
            this.setTitle("查看并修改笔记");
            setSize(450, 700);
            setLocation(800,120);
            note = new Note();
            note.setTitle(FolderView.selectedName);
            LinkedList<Note> notes = noteService.showNoteAll(note);
            titleField.setText(notes.getFirst().getTitle());
            textArea.setText(notes.getFirst().getText());
            idText.setText(notes.getFirst().getId());

        }

        /**
         * 确认修改按钮事件
         * @param e
         */
        private void summitActionPerformed(ActionEvent e) {
            Group group = new Group();
            group.setGroupName(TreeView.selectedName);
            TreeView.selectedId= folderGroupService.getId(group);
            String title = titleField.getText();
            String text = textArea.getText();
            String access = (String) this.accessSelect.getSelectedItem();
            Note note = new Note( title, LoginView.USER.getId(), text, access ,TreeView.selectedId);
            note.setId(idText.getText());
            if(noteService.update(note)!=0){
                JOptionPane.showMessageDialog(null,"修改笔记成功");
                this.dispose();
            }
        }



        private void initComponents(User user) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titleField = new JTextField();
        title = new JLabel();
        scrollPane = new JScrollPane();
        textArea = new JTextArea();
        summit = new JButton();
        accessSelect = new JComboBox<>();
        access = new JLabel();
        label1 = new JLabel();
        idText = new JLabel();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setTitle("\u67e5\u770b\u5e76\u8bbe\u7f6e\u7b14\u8bb0");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- titleField ----
        titleField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, titleField.getFont().getSize() + 6));

        //---- title ----
        title.setText("\u6807\u9898");
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 3f));

        //======== scrollPane ========
        {

            //---- textArea ----
            textArea.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, textArea.getFont().getSize() + 6));
            textArea.setLineWrap(true);
            scrollPane.setViewportView(textArea);
        }

        //---- summit ----
        summit.setText("\u786e\u8ba4\u4fee\u6539");
        summit.addActionListener(e -> summitActionPerformed(e));

        //---- accessSelect ----
        accessSelect.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u516c\u5f00",
            "\u79c1\u6709"
        }));

        //---- access ----
        access.setText("\u6743\u9650");

        //---- label1 ----
        label1.setText("id :");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- idText ----
        idText.setText("16");
        idText.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        idText.setForeground(new Color(0, 153, 153));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(title)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 694, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(titleField, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 474, Short.MAX_VALUE)
                            .addComponent(label1)
                            .addGap(18, 18, 18)
                            .addComponent(idText)
                            .addGap(65, 65, 65))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(access, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(accessSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(251, 251, 251)
                    .addComponent(summit)
                    .addGap(47, 281, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(title, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(titleField, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(idText)
                            .addComponent(label1)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(summit)
                        .addComponent(accessSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(access, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(451, Short.MAX_VALUE))
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
    private JLabel label1;
    private JLabel idText;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
