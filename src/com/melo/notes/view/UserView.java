/*
 * Created by JFormDesigner on Sat Mar 27 21:13:31 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.entity.Announcement;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.AdminServiceImpl;
import com.melo.notes.service.impl.ForumServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;

/**
 * @author Jun
 * @program Note
 * @description 用户界面
 * @date 2021-4
 */
public class UserView extends JFrame {

    /**
     * 相关操作类
     */
    AdminServiceImpl adminService=(AdminServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.AdminService);
    ForumServiceImpl forumService=(ForumServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.ForumService);

    /**
     * 所有公告对象链表
     */
    LinkedList<Announcement> announcements=adminService.showAnnouncementAll();

    /**
     * 选中的论坛id
     */
    private String forumId=null;

    public UserView() {
        initComponents();
        setVisible(true);
        welcome.setText("欢迎回来, "+LoginView.USER.getUserName());
        titleText.setText( "欢迎使用本软件");
        textArea.setText("请注意,本软件仅供学习交流使用,开发者:Melo");
        //根据公告数量初始化下拉框
        for(int i=1;i<=announcements.size();i++){
            selectedPage.addItem(String.valueOf(i));
        }
        //初始化论坛
        list.setListData(forumService.listForumTitle());
        setSize(650, 650);
        setLocation(600, 260);
    }

    /**
     * 各按钮事件
     *
     * @param e
     */
    private void addNoteActionPerformed(ActionEvent e) {
        new AddNoteView(LoginView.USER);
    }


    /**
     * 设置笔记分组
     * @param e
     */
    private void setNoteActionPerformed(ActionEvent e) {
        new FolderView(LoginView.USER);
    }

    /**
     * 查看笔记
     * @param e
     */
    private void showNoteActionPerformed(ActionEvent e) {
        new NoteTableView();
    }

    /**
     * 更改个人信息
     * @param e
     */
    private void updateActionPerformed(ActionEvent e) {
        new PersonalUserView();
    }

    /**
     * 公告页面跳转按钮
     * @param e
     */
    private void toPageActionPerformed(ActionEvent e) {
        int page = Integer.parseInt(selectedPage.getSelectedItem().toString());
        titleText.setText(announcements.get(page-1).getTitle());
        textArea.setText(announcements.get(page-1).getText());
    }

    /**
     * 查看论坛内容
     * @param e
     */
    private void lookActionPerformed(ActionEvent e) {
        if(forumId!=null) {
            new ForumTextView(forumId);
        }else {
            JOptionPane.showMessageDialog(null, Status.FAILED.getMessage());
        }
    }

    /**
     * 新增论坛内容
     * @param e
     */
    private void addForumActionPerformed(ActionEvent e) {
        new AddForumView();
    }

    /**
     * list监听器
     * 获取论坛id
     * @param e
     */
    private void listMouseClicked(MouseEvent e) {
        if(list.getMaxSelectionIndex()!=-1) {
            //截断":"之前的为id
            String idTitle = list.getSelectedValue().toString();
            int location = idTitle.indexOf(":");
            forumId = idTitle.substring(0, location);
        }
    }

    /**
     * 刷新按钮
     * @param e
     */
    private void flushActionPerformed(ActionEvent e) {
        list.setListData(forumService.listForumTitle());
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        note = new JMenu();
        showNote = new JMenuItem();
        setNote = new JMenuItem();
        user = new JMenu();
        update = new JMenuItem();
        welcome = new JLabel();
        titleText = new JLabel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea = new JTextArea();
        selectedPage = new JComboBox();
        toPage = new JButton();
        scrollPane2 = new JScrollPane();
        list = new JList();
        addForum = new JButton();
        look = new JButton();
        flush = new JButton();
        label3 = new JLabel();
        photo = new JLabel();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 1300, 800));
        setMinimumSize(new Dimension(950, 650));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u7528\u6237\u754c\u9762");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar ========
        {
            menuBar.setName("\u65b0\u589e\u7b14\u8bb0");
            menuBar.setAlignmentY(0.8F);
            menuBar.setMinimumSize(new Dimension(59, 50));

            //======== note ========
            {
                note.setText("\u7ba1\u7406\u7b14\u8bb0");
                note.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- showNote ----
                showNote.setText("\u67e5\u770b\u7b14\u8bb0");
                showNote.setFont(new Font("Microsoft YaHei UI", showNote.getFont().getStyle() & ~Font.ITALIC, showNote.getFont().getSize() + 4));
                showNote.addActionListener(e -> showNoteActionPerformed(e));
                note.add(showNote);

                //---- setNote ----
                setNote.setText("\u8bbe\u7f6e\u5206\u7ec4");
                setNote.setFont(new Font("Microsoft YaHei UI", setNote.getFont().getStyle() & ~Font.ITALIC, setNote.getFont().getSize() + 4));
                setNote.addActionListener(e -> setNoteActionPerformed(e));
                note.add(setNote);
            }
            menuBar.add(note);

            //======== user ========
            {
                user.setText("\u8bbe\u7f6e\u4e2a\u4eba\u4fe1\u606f");
                user.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- update ----
                update.setText("\u4fee\u6539\u7528\u6237\u540d\u5bc6\u7801");
                update.setFont(update.getFont().deriveFont(update.getFont().getSize() + 4f));
                update.addActionListener(e -> updateActionPerformed(e));
                user.add(update);
            }
            menuBar.add(user);
        }
        setJMenuBar(menuBar);

        //---- welcome ----
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setText("\u6b22\u8fce\u56de\u6765");
        welcome.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        welcome.setForeground(new Color(102, 255, 255));
        contentPane.add(welcome);
        welcome.setBounds(350, 105, 283, 116);

        //---- titleText ----
        titleText.setForeground(new Color(102, 204, 255));
        contentPane.add(titleText);
        titleText.setBounds(780, 65, 150, 30);

        //---- label2 ----
        label2.setText("\u516c\u544a");
        label2.setForeground(new Color(102, 255, 255));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label2);
        label2.setBounds(835, 20, 50, 35);

        //======== scrollPane1 ========
        {
            scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //---- textArea ----
            textArea.setLineWrap(true);
            textArea.setEditable(false);
            scrollPane1.setViewportView(textArea);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(775, 105, 170, 135);
        contentPane.add(selectedPage);
        selectedPage.setBounds(810, 260, 95, selectedPage.getPreferredSize().height);

        //---- toPage ----
        toPage.setText("\u8df3\u8f6c");
        toPage.addActionListener(e -> toPageActionPerformed(e));
        contentPane.add(toPage);
        toPage.setBounds(825, 310, 75, toPage.getPreferredSize().height);

        //======== scrollPane2 ========
        {

            //---- list ----
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    listMouseClicked(e);
                }
            });
            scrollPane2.setViewportView(list);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(10, 260, 195, 250);

        //---- addForum ----
        addForum.setText("\u5410\u69fd\u4e00\u4e0b");
        addForum.addActionListener(e -> addForumActionPerformed(e));
        contentPane.add(addForum);
        addForum.setBounds(new Rectangle(new Point(10, 530), addForum.getPreferredSize()));

        //---- look ----
        look.setText("\u67e5\u770b");
        look.addActionListener(e -> lookActionPerformed(e));
        contentPane.add(look);
        look.setBounds(new Rectangle(new Point(220, 370), look.getPreferredSize()));

        //---- flush ----
        flush.setText("\u5237\u65b0");
        flush.addActionListener(e -> flushActionPerformed(e));
        contentPane.add(flush);
        flush.setBounds(new Rectangle(new Point(135, 530), flush.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u8bba\u575b");
        label3.setForeground(new Color(102, 204, 255));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setBackground(new Color(102, 255, 255));
        contentPane.add(label3);
        label3.setBounds(70, 220, 50, 35);

        //---- photo ----
        photo.setIcon(new ImageIcon(getClass().getResource("/img/UserView.jpg")));
        contentPane.add(photo);
        photo.setBounds(0, -60, 945, 650);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar;
    private JMenu note;
    private JMenuItem showNote;
    private JMenuItem setNote;
    private JMenu user;
    private JMenuItem update;
    private JLabel welcome;
    private JLabel titleText;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JTextArea textArea;
    private JComboBox selectedPage;
    private JButton toPage;
    private JScrollPane scrollPane2;
    private JList list;
    private JButton addForum;
    private JButton look;
    private JButton flush;
    private JLabel label3;
    private JLabel photo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
