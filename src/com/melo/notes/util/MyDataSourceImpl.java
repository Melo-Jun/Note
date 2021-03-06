package com.melo.notes.util;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author Jun
 * @program Note
 * @description 数据库连接池实现类
 * @date 2021-3-28 20:37
 */
public class MyDataSourceImpl  {
    /**
     * 配置文件路径
     */
    private static String PROP_PATH = "jdbc.properties";
    /**
     * /**
     * 测试数据库连接的等待时长
     */
    private static int TIMEOUT;
    /**
     * 初始连接数
     */
    private static int INIT_SIZE;
    /**
     * 最大连接数
     */
    private static int MAX_SIZE;
    /**
     * 当前已经创建的连接数
     */
    private static int currentCount = 0;

    private static String url;
    private static String user;
    private static String password;
    private static MyDataSourceImpl instance;
    /**
     * 数据库连接池
     */
    private LinkedList<Connection> connPool = new LinkedList<>();

    {
        try {
            /**
             * 加载配置文件
             */
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_PATH));
            /**
             * 驱动类名称
             */
            String driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            user = prop.getProperty("username");
            password = prop.getProperty("password");
            MAX_SIZE = Integer.parseInt(prop.getProperty("MAX_SIZE"));
            INIT_SIZE = Integer.parseInt(prop.getProperty("INIT_SIZE"));
            TIMEOUT = Integer.parseInt(prop.getProperty("TIMEOUT"));
            /**
             * 注册驱动
             */
            Class.forName(driver);
            /**
             * 初始化数据库连接池
             */
            this.initConnection();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 创建数据库连接 (封装操作)
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
     * 初始化数据库连接池
     */
    public void initConnection() {
        for (int i = 0; i < INIT_SIZE; i++) {
            connPool.add(creatConnection());
        }
    }

    /**
     * 从数据库连接池中获取连接
     *
     * @return
     * @throws SQLException
     */
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
            throw new RuntimeException("数据库连接已经到达最大值");
        }
    }


    /**
     * 释放连接，归还到数据库连接池中
     * @param coon
     */
    public void freeConnection(Connection coon) {
        this.connPool.addLast(coon);
    }

    /**
     * 获取当前连接数 (便于操作)
     * @return
     */
    public synchronized int getCurrentConnection() {
        return currentCount;
    }

    /**
     * 获取空闲连接数
     * @return
     */
    public synchronized int getfreeCount() {
        return this.connPool.size();
    }

}
