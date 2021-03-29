/*
 * Created by JFormDesigner on Mon Mar 29 18:53:07 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.User;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;

import static com.melo.notes.util.JdbcUtil.close;

/**
 * @author Jun
 * @program Note
 * @description 笔记类数据库操作实现类
 * @date 2021-3-29 20:00
 */
public class ShowNoteView extends JFrame {

    /**
     * 创建相关操作类对象
     */
    private static NoteDaoImpl noteDao = new NoteDaoImpl();
    /**
     * 标题所在行数
     */
    private static int line = 0;
    private JFrame f = new JFrame();
    private JPanel[] p = new JPanel[3];
    private JLabel[] titleLabel = new JLabel[3];
    static Font titleFont = new Font("微软雅黑", Font.PLAIN, 20);
    static Font mainFont = new Font("微软雅黑", Font.BOLD, 30);
    private Listener l = new Listener();

    public ShowNoteView(User user) {
        /**
         * 设置主标题
         */
        p[line] = new JPanel();
        titleLabel[line] = new JLabel("笔记标题");
        titleLabel[line].setFont(mainFont);
        titleLabel[line].setForeground(Color.BLACK);
        p[line].add(titleLabel[line]);
        f.add(p[line]);
        /**
         * 获取结果集
         */
        ResultSet rs = noteDao.showNoteTitle(user);
        try {
            /**
             * 根据查询结果动态增加行数
             */
            while (rs.next()) {
                line++;
                String title = (String) rs.getObject("title");
                p[line] = new JPanel();
                titleLabel[line] = new JLabel(title);
                titleLabel[line].setFont(titleFont);
                titleLabel[line].setForeground(Color.BLUE);
                /**
                 * 增加鼠标监听器
                 */
                titleLabel[line].addMouseListener(l);
                p[line].add(titleLabel[line]);
                f.add(p[line]);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(null, rs);
        }
        f.setVisible(true);
        f.setBounds(330, 120, 1300, 800);
        f.setTitle("学生界面");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout(FlowLayout.CENTER, 800, 5));
    }


    /**
     * 为各操作进行监听
     */
    public class Listener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 1; i <= line; i++) {
                //根据点击label标题查看笔记详情
                if (e.getSource() == titleLabel[i]) {
                    noteDao.listNoteText(titleLabel[i].getText());
                }
            }

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


        /**
         * 初始化页面
         */
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
}
