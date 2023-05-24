package com.example.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class JDBCutils {

    // 建立与mysql的连接
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 1 载入驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2 定义连接参数,获取数据库连接
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8";
        String username = "root";
        String password = "zrl010906";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    // 释放连接
    public static void releaseResource(Connection conn, PreparedStatement pstmt) throws SQLException {
        if(pstmt!=null){
            pstmt.close();
        }
        if (conn!=null){
            conn.close();
        }
    }
}
