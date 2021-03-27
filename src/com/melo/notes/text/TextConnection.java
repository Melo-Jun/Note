package com.melo.notes.text;

import com.melo.notes.util.JdbcUtil;

import java.sql.Connection;

import static com.melo.notes.util.JdbcUtil.*;

/**
 * @author Jun
 */
public class TextConnection {

    public static void main(String[] args) {
        //先从池子获取连接(初始只有三个)
        Connection conn1= JdbcUtil.getConnection();
        Connection conn2= JdbcUtil.getConnection();
        Connection conn3= JdbcUtil.getConnection();
        //无free回池子，池子已空，则新建连接
        Connection conn4= JdbcUtil.getConnection();
        System.out.println(conn1);
        System.out.println(conn2);
        System.out.println(conn3);
        System.out.println(conn4);
        //池子已空
        System.out.println(getfreeCount());
        //本池子3个，再创一个就4个
        System.out.println(getCurrentConnection());
    }

}
