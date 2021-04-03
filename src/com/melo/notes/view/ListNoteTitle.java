/*
 * Created by JFormDesigner on Sat Apr 03 18:45:09 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.service.impl.FolderGroupServiceImpl;
import com.melo.notes.service.impl.ListNoteTitleServiceImpl;
import com.melo.notes.service.inter.ListNoteTitleService;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author 1
 */
public class ListNoteTitle extends JFrame {
    /**
     * ������ز�������
     * @return
     */
    ListNoteTitleServiceImpl listNoteTitleService=(ListNoteTitleServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.ListNoteTitleService);

    /**
     * ѡ�еı���
     */
    private String title="";

    JFrame jf = new JFrame("���Դ���");
    JButton show=new JButton("�鿴����");
    JButton searchByTitle=new JButton("���ݱ�������");
    JButton searchByAuthor=new JButton("��������id����");
    JPanel panel = new JPanel();
    JList<String> list=new JList<String>();

    public ListNoteTitle() {

        jf.setBounds(330, 120, 650, 600);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


        // ����һ�� JList ʵ��
        panel.add(list);
        //��������ͼ��
        Font titleFont = new Font("΢���ź�", Font.PLAIN, 20);
        list.setFont(titleFont);
        list.setForeground(Color.BLUE);

        //�����ı�������ʾ
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        list.setCellRenderer(renderer);

        //����ÿ�и߶ȿ��
        list.setFixedCellHeight(50);
        list.setFixedCellWidth(300);

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    Integer index = list.locationToIndex(e.getPoint());
                    title = list.getSelectedValue();
                    //To do
                }
                if(e.getSource()==show){
                    list.setListData(new String[]{"��", "ѩ��", "ƻ��", "��֦","jj","bb","eweaw","dasdsa","da"});
                }
            }
        };

        //���Ӽ�����
        list.addMouseListener(mouseListener);
        show.addMouseListener(mouseListener);
        searchByTitle.addMouseListener(mouseListener);
        searchByAuthor.addMouseListener(mouseListener);

        // ����ѡ������
        String[] titles = listNoteTitleService.listNoteTitle();
        list.setListData(titles);

        //���ù������
        JScrollPane scrollPane = new JScrollPane(
                list,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        // ��ӵ������������

        panel.add(show);
        panel.add(searchByTitle);
        panel.add(searchByAuthor);
        panel.add(scrollPane);


        jf.setContentPane(panel);
        jf.setVisible(true);

    }















    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        //======== this ========
        Container contentPane = getContentPane();

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 400, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 300, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
