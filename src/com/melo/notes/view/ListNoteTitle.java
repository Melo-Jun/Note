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
     * 创建相关操作对象
     * @return
     */
    ListNoteTitleServiceImpl listNoteTitleService=(ListNoteTitleServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.ListNoteTitleService);

    /**
     * 选中的标题
     */
    private String title="";
    private final String PRIVATE="私有";
    private final String PUBLIC="公开";

    JFrame jf = new JFrame("查看笔记");
    JButton show=new JButton("查看详情");
    JButton searchByTitle=new JButton("根据标题搜索");
    JButton searchByAuthor=new JButton("根据作者id搜索");
    JButton back=new JButton("返回");
    JPanel panel = new JPanel();
    JList<String> list=new JList();
    JTextArea text=new JTextArea();
    /**
     * 获取所有作者公开的笔记标题
     */
    String[] titles = listNoteTitleService.listNoteTitle(new AuthorBean(null,PUBLIC));

    public ListNoteTitle() {

        jf.setBounds(330, 120, 650, 900);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        text.setBounds(100,500,200,200);

        // 创建一个 JList 实例
        panel.add(list);
        panel.add(text);
        //设置字体和间距
        Font titleFont = new Font("微软雅黑", Font.PLAIN, 20);
        list.setFont(titleFont);
        list.setForeground(Color.BLUE);

        //设置文本居中显示
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        list.setCellRenderer(renderer);

        //设置每行高度宽度
        list.setFixedCellHeight(50);
        list.setFixedCellWidth(500);

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /**
                 * 显示详情
                 */
                if(e.getSource()==show){
                    NoteTextBean noteTextBean = new NoteTextBean(title);
                    String[] strings = listNoteTitleService.listNoteAll(noteTextBean);
                    list.setListData(strings);
                    new NoteTextView(listNoteTitleService.showNoteText(noteTextBean)).setVisible(true);
                }
                /**
                 * 根据标题搜索
                 */
                if(e.getSource()==searchByTitle){
                    String title = JOptionPane.showInputDialog("请输入标题");
                    NoteBean noteBean = new NoteBean(PUBLIC,title);
                    list.setListData(listNoteTitleService.listNoteTitle(noteBean));
                }
                if(e.getSource()==searchByAuthor){
                    String authorId = JOptionPane.showInputDialog("请输入作者id");
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

        //增加监听器
        list.addMouseListener(mouseListener);
        show.addMouseListener(mouseListener);
        searchByTitle.addMouseListener(mouseListener);
        searchByAuthor.addMouseListener(mouseListener);
        back.addMouseListener(mouseListener);

        // 设置选项数据(默认显示所有笔记)
        //String[] titles = listNoteTitleService.listNoteTitle(new AuthorBean(null,PUBLIC));
        list.setListData(titles);

        //设置滚动面板
        JScrollPane scrollPane = new JScrollPane(
                list,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        // 添加到内容面板容器

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
