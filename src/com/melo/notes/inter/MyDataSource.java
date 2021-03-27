package com.melo.notes.inter;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Jun
 * @program Note
 * @description ���ݿ����ӳؽӿ�
 * @date 2021-3-27 10��27
 */
public interface MyDataSource {
    /**
     * ��ȡ���ݿ�����
     * @return
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * �ͷ�����
     * @param coon
     */
    void freeConnection(Connection coon);

    /**
     * ��ȡ��ǰ������
     * @return
     */
    int getCurrentConnection();

    /**
     * ��ȡ����������
     * @return
     */
    int getfreeCount() ;
}



