package com.aa.bb;

import java.sql.*;

/**
 * 数据库连接类
 */
public class dbConnect {
    String uri = "jdbc:mysql://localhost:3306/expense?serverTimezone=UTC&useSSL=true";
    String user = "root";
    String password = "root";

    /**
     * 连接数据库
     * @return 数据库的连接
     */
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

    /**
     * 断开连接
     * @param connection 数据库的连接
     */
    public void closeConnect(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        dbConnect dbConnect = new dbConnect();
        Connection con = dbConnect.getConnect();
    }
}