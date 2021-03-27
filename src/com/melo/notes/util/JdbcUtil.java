package com.melo.notes.util;
import com.melo.notes.exception.DaoException;
import com.melo.notes.impl.BaseDaoImpl;
import com.melo.notes.impl.MyDataSourceImpl;
import com.melo.notes.inter.MyDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author Jun
 */
public class JdbcUtil {


    private final static String PROP_PATH = "jdbc.properties";
    /**
     * 释放资源
     * @param ps
     * @param rs
     * @param conn
     */
    public static void close(PreparedStatement ps, ResultSet rs, Connection conn) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    private static MyDataSource dataSrc = new MyDataSourceImpl();


    /**
     * 获取数据库连接
     * @return
     */
    public synchronized static Connection getConnection(){
        try {
            return dataSrc.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前连接数
     * @return
     */
    public synchronized static int getCurrentConnection() {
        return dataSrc.getCurrentConnection();
    }

    /**
     * 获取空闲连接数
     * @return
     */
    public static int getfreeCount() {
        return dataSrc.getfreeCount();
    }


    /**
     * 将连接放回数据库连接池中
     * @param coon
     */
    public static void freeConnection(Connection coon){
        dataSrc.freeConnection(coon);
    }

    /**
     * 根据实际情况配置相应的PreparedStatement参数
     * @param ps
     * @param obj 传入的对象
     */
    public static void setParam(PreparedStatement ps,Object obj){

        LinkedList fieldNames = new LinkedList<>();
        LinkedList fieldValues = new LinkedList<>();
        BaseDaoImpl.getInstance().fieldMapper(obj,fieldNames,fieldValues);
        Object[] params = fieldValues.toArray();
        for(int i=1;i<=params.length;i++){
                try {
                    ps.setObject(i, params[i-1]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    }

    /**
     * 根据类获取对应的表名
     * @param key
     * @return
     */
    public static String getTableName(String key){
        try {
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_PATH));
            return key == null? null : prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DaoException("无法加载配置文件:"+PROP_PATH, e);
        }
    }

    /**
     * 根据类名获取对应的表名
     * @param clazz
     * @return
     */
    public static String getTableName(Class clazz){
        try {
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_PATH));
            return clazz == null? null : prop.getProperty(clazz.getSimpleName());
        } catch (IOException e) {
            e.printStackTrace();
            throw new DaoException("无法加载配置文件:"+PROP_PATH, e);
        }
    }
}
