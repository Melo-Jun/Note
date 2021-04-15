/*
 * Created by JFormDesigner on Sun Apr 11 17:36:35 CST 2021
 */

package com.melo.notes.view;

import com.melo.notes.entity.Note;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.NoteServiceImpl;
import com.melo.notes.service.impl.UserNoteServiceImpl;
import com.melo.notes.util.BeanFactory;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author Jun
 * @program Note
 * @description �û��ʼ���Ϣ����
 * @date 2021-4
 */
public class UserNoteView extends JFrame {

    /**
     * ������ز�������
     */
    private final UserNoteServiceImpl userNoteService=(UserNoteServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.UserNoteService);
    private final NoteServiceImpl noteService =(NoteServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.NoteService);

    private DefaultTableModel model= null;
    private JTable table=null;
    /**
     * ���û����бʼǶ���
     */
    private Note allNote=new Note();
    /**
     * ѡ�еıʼ�id
     */
    private String noteId=null;

    /**
     *���û��ʼ�����
     */
    private int noteCount =0;

    /**
     *���û��ʼǷ�������
     */
    private String groupCount =null;

    public UserNoteView(String userId) {
        initComponents();
        setVisible(true);
        allNote.setAuthorId(userId);
        fillTable(allNote);
        noteCount =model.getRowCount();
        groupCount =userNoteService.countGroup(userId);
        information.setText("�ʼ�����:"+ noteCount +"\n"+"�ʼǷ�������:"+ groupCount +"\n");
    }

    /**
     * ������
     * @param e
     */
    private void table1MouseClicked(MouseEvent e) {
        int row=table.getSelectedRow();
        if(row!=-1) {
            noteId=(String) model.getValueAt(row, 0);
        }
    }

    /**
     * �鿴�û��ʼ��ı�����
     * @param e
     */
    private void noteTextActionPerformed(ActionEvent e) {
        new NoteTextView(noteService.showNoteText(noteId));
    }

    /**
     * ɾ���û��ʼ�
     * @param e
     */
    private void deleteActionPerformed(ActionEvent e) {
        if(noteService.delete(noteId)!=0) {
            System.out.println("�ɹ���");
            JOptionPane.showMessageDialog(null, Status.SUCCESS.getMessage());
        }else {
            System.out.println("ʧ����");
            JOptionPane.showMessageDialog(null,Status.FAILED.getMessage());
        }
    }

    /**
     * ˢ�²�����ť
     * @param e
     */
    private void backActionPerformed(ActionEvent e) {
        fillTable(allNote);
    }

    /**
     * �����
     * @param note ��ض���
     */
    private void fillTable(Note note){
        model= (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        LinkedList<Note> notes = noteService.showNoteAll(note);
        for(Note tempNote:notes) {
            model.addRow(userNoteService.fillTable(tempNote));
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table = new JTable();
        noteText = new JButton();
        back = new JButton();
        information = new JTextArea();
        delete = new JButton();

        //======== this ========
        setTitle("\u7528\u6237\u7b14\u8bb0\u4fe1\u606f");
        setIconImage(new ImageIcon(getClass().getResource("/img/blueLogo(new).png")).getImage());
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {
            scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //---- table ----
            table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "id", "title", "likeCount", "locatedGroup"
                }
            ));
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            table.setFillsViewportHeight(true);
            table.setAlignmentY(1.0F);
            table.setAutoCreateRowSorter(true);
            table.setPreferredScrollableViewportSize(new Dimension(450, 500));
            table.setShowVerticalLines(false);
            table.setShowHorizontalLines(false);
            table.setRowHeight(20);
            table.setAlignmentX(1.0F);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table);
        }

        //---- noteText ----
        noteText.setText("\u67e5\u770b\u7528\u6237\u7b14\u8bb0\u5185\u5bb9");
        noteText.addActionListener(e -> noteTextActionPerformed(e));

        //---- back ----
        back.setText("\u5237\u65b0");
        back.addActionListener(e -> backActionPerformed(e));

        //---- information ----
        information.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        information.setEditable(false);

        //---- delete ----
        delete.setText("\u5220\u9664");
        delete.addActionListener(e -> deleteActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(information, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                            .addGap(230, 230, 230)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(noteText)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(delete)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(back)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(noteText)
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(back)
                                .addComponent(delete)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(information, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(46, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JButton noteText;
    private JButton back;
    private JTextArea information;
    private JButton delete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
