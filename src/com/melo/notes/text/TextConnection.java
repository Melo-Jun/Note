package com.melo.notes.text;

import com.melo.notes.util.JdbcUtils;

import java.sql.Connection;

import static com.melo.notes.util.JdbcUtils.*;

/**
 * @author Jun
 */
public class TextConnection {

    public static void main(String[] args) {
        //先从池子获取连接(初始只有三个)
        Connection conn1= JdbcUtils.getConnection();
        Connection conn2= JdbcUtils.getConnection();
        Connection conn3= JdbcUtils.getConnection();
        //无free回池子，池子已空，则新建连接
        Connection conn4= JdbcUtils.getConnection();
        System.out.println(conn1);
        System.out.println(conn2);
        System.out.println(conn3);
        System.out.println(conn4);
        //池子已空
        System.out.println(getFreeCount());
        //本池子3个，再创一个就4个
        System.out.println(getCurrentConnection());
    }

}
