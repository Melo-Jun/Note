/*
 * Created by JFormDesigner on Sat Apr 03 18:45:09 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.bean.AuthorBean;
import com.melo.notes.bean.NoteBean;
import com.melo.notes.bean.NoteTextBean;
import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Note;
import com.melo.notes.service.impl.ListNoteTitleServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.temporal.JulianFields;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jun
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
    private final String PRIVATE="˽��";
    private final String PUBLIC="����";

    JFrame jf = new JFrame("�鿴�ʼ�");
    JButton show=new JButton("�鿴����");
    JButton searchByTitle=new JButton("���ݱ�������");
    JButton searchByAuthor=new JButton("��������id����");
    JButton back=new JButton("����");
    JPanel panel = new JPanel();
    JList<String> list=new JList();
    JTextArea text=new JTextArea();
    /**
     * ��ȡ�������߹����ıʼǱ���
     */
    String[] titles = listNoteTitleService.listNoteTitle(new AuthorBean(null,PUBLIC));

    public ListNoteTitle() {

        jf.setBounds(330, 120, 650, 900);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        text.setBounds(100,500,200,200);

        // ����һ�� JList ʵ��
        panel.add(list);
        panel.add(text);
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
        list.setFixedCellWidth(500);

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /**
                 * ��ʾ����
                 */
                if(e.getSource()==show){
                    NoteTextBean noteTextBean = new NoteTextBean(title);
                    String[] strings = listNoteTitleService.listNoteAll(noteTextBean);
                    list.setListData(strings);
                    new NoteTextView(listNoteTitleService.showNoteText(noteTextBean)).setVisible(true);
                }
                /**
                 * ���ݱ�������
                 */
                if(e.getSource()==searchByTitle){
                    String title = JOptionPane.showInputDialog("���������");
                    NoteBean noteBean = new NoteBean(PUBLIC,title);
                    list.setListData(listNoteTitleService.listNoteTitle(noteBean));
                }
                if(e.getSource()==searchByAuthor){
                    String authorId = JOptionPane.showInputDialog("����������id");
                    AuthorBean authorBean = new AuthorBean(PUBLIC,authorId);
                    list.setListData(listNoteTitleService.listNoteTitle(authorBean));
                }
                if(e.getSource()==back){
                    list.setListData(titles);
                }
                Integer index = list.locationToIndex(e.getPoint());
                if(list.getSelectedValue()!=null) {
                    Integer i = list.getSelectedValue().lastIndexOf(":");
                    System.out.println(i);
                }
                title = list.getSelectedValue();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        //���Ӽ�����
        list.addMouseListener(mouseListener);
        show.addMouseListener(mouseListener);
        searchByTitle.addMouseListener(mouseListener);
        searchByAuthor.addMouseListener(mouseListener);
        back.addMouseListener(mouseListener);

        // ����ѡ������(Ĭ����ʾ���бʼ�)
        //String[] titles = listNoteTitleService.listNoteTitle(new AuthorBean(null,PUBLIC));
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
        panel.add(back);
        panel.add(text);
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
