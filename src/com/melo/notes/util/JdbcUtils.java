package com.melo.notes.util;
import com.melo.notes.exception.DaoException;
import com.melo.notes.dao.impl.BaseDaoImpl;


import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author Jun
 */
public class JdbcUtils {

    private static final BaseDaoImpl baseDao=(BaseDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.BaseDao);

    private final static String PROP_PATH = "db.properties";
    /**
     * 释放资源
     * @param ps
     * @param rs
     */
    public static void close(PreparedStatement ps, ResultSet rs) {
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
    }

    /**
     * 释放资源
     * @param stmt
     * @param rs
     */
    public static void close(Statement stmt, ResultSet rs) {
        if (stmt != null) {
            try {
                stmt.close();
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
    }

    private static MyDataSourceImpl dataSrc = new MyDataSourceImpl();


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
    public static int getFreeCount() {
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
    public static void setParams(PreparedStatement ps, Object obj) {

        LinkedList fieldNames = new LinkedList<>();
        LinkedList fieldValues = new LinkedList<>();
        baseDao.fieldMapper(obj, fieldNames, fieldValues);
        Object[] params = fieldValues.toArray();
        System.out.println(fieldValues);
        for (int i = 0; i < params.length; i++) {
            try {
                ps.setObject(i + 1, params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据类获取对应的表名
     * @param key 键值
     * @return 对应表名
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
     * @return java.lang.String
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

    /**
     * 负责返回对象对应的表名
     *
     * @param obj 查询表名的对象
     * @return java.lang.String
     */
    public static String getTableName(Object obj) {
        return obj == null ? null : getTableName(obj.getClass().getSimpleName());
    }
}

