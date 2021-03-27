package com.melo.notes.impl;


import com.melo.notes.inter.MyDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author Jun
 * @program Note
 * @description ���ݿ����ӳ�ʵ����
 * @date 2021-3-27 10:30
 */
public class MyDataSourceImpl implements MyDataSource {
    /**
     * �����ļ�·��
     */
    private static String PROP_PATH = "jdbc.properties";
    /**
     * /**
     * �������ݿ����ӵĵȴ�ʱ��
     */
    private static int TIMEOUT;
    /**
     * ��ʼ������
     */
    private static int INIT_SIZE;
    /**
     * ���������
     */
    private static int MAX_SIZE;
    /**
     * ��ǰ�Ѿ�������������
     */
    private static int currentCount = 0;

    private static String url;
    private static String user;
    private static String password;
    private static MyDataSourceImpl instance;
    /**
     * ���ݿ����ӳ�
     */
    private LinkedList<Connection> connPool = new LinkedList<>();

    {
        try {
            /**
             * ���������ļ�
             */
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_PATH));
            /**
             * ����������
             */
            String driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            user = prop.getProperty("username");
            password = prop.getProperty("password");
            MAX_SIZE = Integer.parseInt(prop.getProperty("MAX_SIZE"));
            INIT_SIZE = Integer.parseInt(prop.getProperty("INIT_SIZE"));
            TIMEOUT = Integer.parseInt(prop.getProperty("TIMEOUT"));
            /**
             * ע������
             */
            Class.forName(driver);
            /**
             * ��ʼ�����ݿ����ӳ�
             */
            this.initConnection();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * �������ݿ����� (��װ����)
     *
     * @return Connection
     */
    public synchronized Connection creatConnection() {
        currentCount++;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * ��ʼ�����ݿ����ӳ�
     */
    public void initConnection() {
        for (int i = 0; i < INIT_SIZE; i++) {
            connPool.add(creatConnection());
        }
    }

    /**
     * �����ݿ����ӳ��л�ȡ����
     *
     * @return
     * @throws SQLException
     */
    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (connPool.size() > 0) {
            Connection conn = connPool.removeLast();
            if(conn.isValid(TIMEOUT)){
                return conn;
            }else {
                return creatConnection();
            }
        } else if (currentCount < MAX_SIZE) {
            return creatConnection();
        } else {
            throw new RuntimeException("���ݿ������Ѿ��������ֵ");
        }
    }


    /**
     * �ͷ����ӣ��黹�����ݿ����ӳ���
     * @param coon
     */
    @Override
    public void freeConnection(Connection coon) {
        this.connPool.addLast(coon);
    }

    /**
     * ��ȡ��ǰ������ (���ڲ���)
     * @return
     */
    @Override
    public synchronized int getCurrentConnection() {
        return currentCount;
    }

    /**
     * ��ȡ����������
     * @return
     */
    @Override
    public synchronized int getfreeCount() {
        return this.connPool.size();
    }
}
