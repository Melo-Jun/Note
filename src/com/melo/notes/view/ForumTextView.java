/*
 * Created by JFormDesigner on Thu Apr 15 16:10:32 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.entity.Forum;
import com.melo.notes.entity.abs.BaseEntity;
import com.melo.notes.service.impl.ForumServiceImpl;
import com.melo.notes.service.impl.NoteTableServiceImpl;
import com.melo.notes.service.impl.UserNoteServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description 论坛内容界面
 * @date 2021-4-15 17:02
 */
public class ForumTextView extends JFrame {

    /**
     * 相关操作类
     */
    ForumServiceImpl forumService=(ForumServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.ForumService);

    public ForumTextView(String forumId) {
        initComponents();
        setVisible(true);
        setSize(650, 550);
        setLocation(600, 360);
        LinkedList<Forum> forums = forumService.showForumText(forumId);
        Forum forum = forums.getFirst();
        titleField.setText(forum.getTitle());
        textArea.setText(forum.getText());
        userName.setText(new NoteTableServiceImpl().showNoteAuthor(forum.getUserId()));
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titleField = new JTextField();
        title = new JLabel();
        scrollPane = new JScrollPane();
        textArea = new JTextArea();
        label1 = new JLabel();
        userName = new JLabel();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setTitle("\u8bba\u575b\u5185\u5bb9");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- titleField ----
        titleField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        titleField.setEditable(false);

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
            textArea.setEditable(false);
            scrollPane.setViewportView(textArea);
        }

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\u79f0 :");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        //---- userName ----
        userName.setText("16");
        userName.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        userName.setForeground(new Color(0, 153, 153));

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
                            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(11, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(363, 363, 363)
                    .addComponent(title)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                    .addComponent(label1)
                    .addGap(18, 18, 18)
                    .addComponent(userName)
                    .addGap(69, 69, 69))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(userName)
                            .addComponent(label1)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(titleField)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(150, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField titleField;
    private JLabel title;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JLabel label1;
    private JLabel userName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
