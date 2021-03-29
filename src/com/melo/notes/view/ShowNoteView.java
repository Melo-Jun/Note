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
 * @description �ʼ������ݿ����ʵ����
 * @date 2021-3-29 20:00
 */
public class ShowNoteView extends JFrame {

    /**
     * ������ز��������
     */
    private static NoteDaoImpl noteDao = new NoteDaoImpl();
    /**
     * ������������
     */
    private static int line = 0;
    private JFrame f = new JFrame();
    private JPanel[] p = new JPanel[3];
    private JLabel[] titleLabel = new JLabel[3];
    static Font titleFont = new Font("΢���ź�", Font.PLAIN, 20);
    static Font mainFont = new Font("΢���ź�", Font.BOLD, 30);
    private Listener l = new Listener();

    public ShowNoteView(User user) {
        /**
         * ����������
         */
        p[line] = new JPanel();
        titleLabel[line] = new JLabel("�ʼǱ���");
        titleLabel[line].setFont(mainFont);
        titleLabel[line].setForeground(Color.BLACK);
        p[line].add(titleLabel[line]);
        f.add(p[line]);
        /**
         * ��ȡ�����
         */
        ResultSet rs = noteDao.showNoteTitle(user);
        try {
            /**
             * ���ݲ�ѯ�����̬��������
             */
            while (rs.next()) {
                line++;
                String title = (String) rs.getObject("title");
                p[line] = new JPanel();
                titleLabel[line] = new JLabel(title);
                titleLabel[line].setFont(titleFont);
                titleLabel[line].setForeground(Color.BLUE);
                /**
                 * ������������
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
        f.setTitle("ѧ������");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout(FlowLayout.CENTER, 800, 5));
    }


    /**
     * Ϊ���������м���
     */
    public class Listener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 1; i <= line; i++) {
                //���ݵ��label����鿴�ʼ�����
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
         * ��ʼ��ҳ��
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
