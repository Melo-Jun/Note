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
     * 创建相关操作对象
     * @return
     */
    ListNoteTitleServiceImpl listNoteTitleService=(ListNoteTitleServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.ListNoteTitleService);

    /**
     * 选中的标题
     */
    private String title="";

    JFrame jf = new JFrame("测试窗口");
    JButton show=new JButton("查看详情");
    JButton searchByTitle=new JButton("根据标题搜索");
    JButton searchByAuthor=new JButton("根据作者id搜索");
    JPanel panel = new JPanel();
    JList<String> list=new JList<String>();

    public ListNoteTitle() {

        jf.setBounds(330, 120, 650, 600);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


        // 创建一个 JList 实例
        panel.add(list);
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
                    list.setListData(new String[]{"想", "雪梨", "苹果", "荔枝","jj","bb","eweaw","dasdsa","da"});
                }
            }
        };

        //增加监听器
        list.addMouseListener(mouseListener);
        show.addMouseListener(mouseListener);
        searchByTitle.addMouseListener(mouseListener);
        searchByAuthor.addMouseListener(mouseListener);

        // 设置选项数据
        String[] titles = listNoteTitleService.listNoteTitle();
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
