/*
 * Created by JFormDesigner on Sat Mar 27 20:56:15 CST 2021
 */

package com.melo.notes.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * @author Jun
 */
public class TreeView extends JFrame {

    public static String locatedGroup="";

    public String getLocatedGroup() {
        return locatedGroup;
    }

    public TreeView() {
        //initComponents();
        JFrame jf = new JFrame("���Դ���");
        JButton submit = new JButton("ȷ��");
        jf.setSize(300, 300);
        jf.setLocation(1100, 400);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        panel.setLocation(800, 400);
        panel2.setLocation(800, 500);
        //panel.setLocation();

        // �������ڵ�
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("�й�");
        DefaultMutableTreeNode rootNode2 = new DefaultMutableTreeNode("��");

        // ���������ڵ�
        DefaultMutableTreeNode gdNode = new DefaultMutableTreeNode("�㶫");
        DefaultMutableTreeNode fjNode = new DefaultMutableTreeNode("����");
        DefaultMutableTreeNode shNode = new DefaultMutableTreeNode("�Ϻ�");
        DefaultMutableTreeNode twNode = new DefaultMutableTreeNode("̨��");

        // �Ѷ����ڵ���Ϊ�ӽڵ���ӵ����ڵ�
        rootNode.add(gdNode);
        rootNode.add(fjNode);
        rootNode.add(shNode);
        rootNode.add(twNode);

        // ���������ڵ�
        DefaultMutableTreeNode gzNode = new DefaultMutableTreeNode("����");
        DefaultMutableTreeNode szNode = new DefaultMutableTreeNode("����");

        DefaultMutableTreeNode fzNode = new DefaultMutableTreeNode("����");
        DefaultMutableTreeNode xmNode = new DefaultMutableTreeNode("����");

        DefaultMutableTreeNode tbNode = new DefaultMutableTreeNode("̨��");
        DefaultMutableTreeNode gxNode = new DefaultMutableTreeNode("����");
        DefaultMutableTreeNode jlNode = new DefaultMutableTreeNode("��¡");

        // �������ڵ���Ϊ�ӽڵ���ӵ���Ӧ�Ķ����ڵ�
        gdNode.add(gzNode);
        gdNode.add(szNode);

        fjNode.add(fzNode);
        fjNode.add(xmNode);

        twNode.add(tbNode);
        twNode.add(gxNode);
        twNode.add(jlNode);


        //�����ļ��ڵ�
        DefaultMutableTreeNode gzNode2 = new DefaultMutableTreeNode("������");
        gzNode.add(gzNode2);

        // ʹ�ø��ڵ㴴�������
        JTree tree = new JTree(rootNode);
        JTree tree2 = new JTree(rootNode2);

        // ��������ʾ���ڵ���
        tree.setShowsRootHandles(true);
        tree2.setShowsRootHandles(true);

        // �������ڵ�ɱ༭
        tree.setEditable(true);
        tree2.setShowsRootHandles(true);

        // ���ýڵ�ѡ�м�����
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                locatedGroup= e.getPath().getLastPathComponent().toString();
                System.out.println(locatedGroup);
            }
        });


        // ����������壬����������Ϊ���ڵ�չ���������Ҫ�ܴ�Ŀռ�����ʾ��������Ҫ��һ�����������������
        JScrollPane scrollPane = new JScrollPane(tree);
        JScrollPane scrollPane2 = new JScrollPane(tree2);

        // ��ӹ�����嵽�������
        panel.add(scrollPane);
        panel2.add(scrollPane2);
        panel.add(submit, BorderLayout.SOUTH);

        // ���ô���������岢��ʾ
        jf.setContentPane(panel);
        jf.setVisible(true);
    }

    /**
     * ȷ����ť�¼�
     */
   /* private void submitActionPerformed(ActionEvent e1,TreeSelectionEvent e2) {
        e2.getPath()
    }*/


    private void initComponents () {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            scrollPane1 = new JScrollPane();
            tree1 = new JTree();

            //======== this ========
            Container contentPane = getContentPane();

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(tree1);
            }

            GroupLayout contentPaneLayout = new GroupLayout(contentPane);
            contentPane.setLayout(contentPaneLayout);
            contentPaneLayout.setHorizontalGroup(
                    contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(123, 123, 123)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(197, Short.MAX_VALUE))
            );
            contentPaneLayout.setVerticalGroup(
                    contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(67, Short.MAX_VALUE))
            );
            pack();
            setLocationRelativeTo(getOwner());
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        private JScrollPane scrollPane1;
        private JTree tree1;
        // JFormDesigner - End of variables declaration  //GEN-END:variables
}
