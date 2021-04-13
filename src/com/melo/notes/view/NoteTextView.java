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
 * @description �ʼ��ı����ݽ���
 * @date 2021-4
 */
public class NoteTextView extends JFrame {
    /**
     * ÿҳ����
     */
    private final int size=100;
    /**
     * ���ҳ��
     */
    private int maxPage=0;

    /**
     * �ı�����
     */
    private static String text=null;

    public NoteTextView(String text) {
        initComponents();
        //�洢�ı�����
        NoteTextView.text=text;
        //�ʼ�����С��ÿҳĬ������
        if(text.length()<=size){
            maxPage=1;
        }else {
            int temp = (text.length()) % size;
            maxPage = temp == 0 ? text.length()/size : text.length()/size+1;
        }
        //���ҳ����
        for(int i=0;i<=maxPage;i++){
            selectedPage.addItem(String.valueOf(i));
        }

        setVisible(true);
        this.setTitle("�ʼ�����");
        setBounds(700,566,520,400);
        noteText.setText("ȫ������ԼΪ:"+text.length()+"\n"+"��ҳ��������Ķ�"+"\n");
    }

    /**
     * ��תҳ��
     * @notice ��Ҫѡ��ҳ������ת��ť
     * @param e
     */
    private void toPageActionPerformed(ActionEvent e) {
        //��ǰҳ��
        int currentPage = Integer.parseInt(selectedPage.getSelectedItem().toString()) ;
        //����ҳ
        if (currentPage==0) {
            noteText.setText("ȫ������ԼΪ:" + text.length() + "\n" + "��ҳ��������Ķ�" + "\n");
        }
        //����ҳ��
        else if(currentPage<maxPage) {
            noteText.setText(text.substring((currentPage-1) * size, currentPage * size ));
        }
        //���һҳ
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
