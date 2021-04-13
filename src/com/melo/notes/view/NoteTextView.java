/*
 * Created by JFormDesigner on Sun Apr 04 18:14:14 CST 2021
 */

package com.melo.notes.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
 * @program Note
 * @description 笔记文本内容界面
 * @date 2021-4
 */
public class NoteTextView extends JFrame {
    /**
     * 每页字数
     */
    private final int size=100;
    /**
     * 最大页数
     */
    private int maxPage=0;

    /**
     * 文本内容
     */
    private static String text=null;

    public NoteTextView(String text) {
        initComponents();
        //存储文本内容
        NoteTextView.text=text;
        //笔记字数小于每页默认字数
        if(text.length()<=size){
            maxPage=1;
        }else {
            int temp = (text.length()) % size;
            maxPage = temp == 0 ? text.length()/size : text.length()/size+1;
        }
        //填充页数框
        for(int i=0;i<=maxPage;i++){
            selectedPage.addItem(String.valueOf(i));
        }

        setVisible(true);
        this.setTitle("笔记内容");
        setBounds(700,566,520,400);
        noteText.setText("全文字数约为:"+text.length()+"\n"+"翻页开启你的阅读"+"\n");
    }

    /**
     * 跳转页面
     * @notice 需要选择页数后按跳转按钮
     * @param e
     */
    private void toPageActionPerformed(ActionEvent e) {
        //当前页数
        int currentPage = Integer.parseInt(selectedPage.getSelectedItem().toString()) ;
        //导航页
        if (currentPage==0) {
            noteText.setText("全文字数约为:" + text.length() + "\n" + "翻页开启你的阅读" + "\n");
        }
        //其他页面
        else if(currentPage<maxPage) {
            noteText.setText(text.substring((currentPage-1) * size, currentPage * size ));
        }
        //最后一页
        else {
            noteText.setText(text.substring((currentPage-1) * size));
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        noteText = new JTextArea();
        selectedPage = new JComboBox();
        page = new JLabel();
        toPage = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- noteText ----
            noteText.setEditable(false);
            noteText.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            noteText.setLineWrap(true);
            scrollPane1.setViewportView(noteText);
        }

        //---- page ----
        page.setText("\u9875\u6570");
        page.setHorizontalAlignment(SwingConstants.CENTER);

        //---- toPage ----
        toPage.setText("\u8df3\u8f6c");
        toPage.addActionListener(e -> toPageActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(selectedPage, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(page, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                .addComponent(toPage))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addComponent(page, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(selectedPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(toPage)
                            .addGap(0, 84, Short.MAX_VALUE))
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTextArea noteText;
    private JComboBox selectedPage;
    private JLabel page;
    private JButton toPage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
