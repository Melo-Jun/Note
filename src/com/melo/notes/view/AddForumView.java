/*
 * Created by JFormDesigner on Thu Apr 15 17:04:48 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.ForumServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description 论坛内容界面
 * @date 2021-4-15 17:05
 */
public class AddForumView extends JFrame {

    /**
     * 相关操作类
     */
    ForumServiceImpl forumService=(ForumServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.ForumService);

    public AddForumView() {
        initComponents();
        setVisible(true);
        setSize(650, 550);
        setLocation(600, 360);
    }

    /**
     * 确认增加按钮
     * @param e
     */
    private void submitActionPerformed(ActionEvent e) {
        String title = titleField.getText();
        String text = textArea.getText();
        if(forumService.addForum(title,text)){
            JOptionPane.showMessageDialog(null, Status.SUCCESS.getMessage());
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(null,Status.FAILED.getMessage());
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titleField = new JTextField();
        title = new JLabel();
        scrollPane = new JScrollPane();
        textArea = new JTextArea();
        submit = new JButton();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setTitle("\u8bba\u575b\u5185\u5bb9");
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

        //---- submit ----
        submit.setText("\u786e\u8ba4");
        submit.addActionListener(e -> submitActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(315, 315, 315)
                            .addComponent(titleField, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(63, 63, 63)
                            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(363, 363, 363)
                            .addComponent(title)))
                    .addContainerGap(11, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 307, Short.MAX_VALUE)
                    .addComponent(submit)
                    .addGap(286, 286, 286))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(title, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(titleField)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(submit)
                    .addContainerGap(93, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField titleField;
    private JLabel title;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JButton submit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
