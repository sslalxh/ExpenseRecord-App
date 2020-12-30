package com.aa.bb;

import java.sql.*;

public class dbConnect {
    String uri = "jdbc:mysql://localhost:3306/expense?serverTimezone=UTC&useSSL=true";
    String user = "root";
    String password = "root";

    public Connection getConnect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(uri, user, password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return con;
    }

    public void closeConnect(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}