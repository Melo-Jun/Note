package com.melo.notes.inter;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Jun
 * @program Note
 * @description 数据库连接池接口
 * @date 2021-3-27 10：27
 */
public interface MyDataSource {
    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * 释放连接
     * @param coon
     */
    void freeConnection(Connection coon);

    /**
     * 获取当前连接数
     * @return
     */
    int getCurrentConnection();

    /**
     * 获取空闲连接数
     * @return
     */
    int getfreeCount() ;
}



