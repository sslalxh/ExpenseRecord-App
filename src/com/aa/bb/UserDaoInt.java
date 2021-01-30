package com.aa.bb;

import java.sql.*;

/**
 * DAO实现类，拓展了UserDao接口
 */
public class UserDaoInt implements UserDao {
    @Override
    public User login(Connection con, User user) throws SQLException {
        User outUser = null;
        String Sql = "SELECT * FROM user WHERE userName = ? AND password = ?";  //sql语句
        PreparedStatement pstmt = con.prepareStatement(Sql);   //获取预编译的数据库操作对象
        pstmt.setString(1, user.getUserName());  //设置特点位置的参数
        pstmt.setString(2, user.getPassword());

        ResultSet rs = pstmt.executeQuery();  //执行查询语句，返回结果集
        if (rs.next()) {
            outUser = new User();
            outUser.setUserName(rs.getString("userName"));
            outUser.setPassword(rs.getString("password"));
        }
        return outUser;
    }

    @Override
    public int register(Connection con, User user) throws SQLException {
        String Sql = "INSERT INTO user (userName, password) VALUES (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(Sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());

        return pstmt.executeUpdate();  //返回sql语句更新的行数
    }

    @Override
    public boolean checkUser(Connection con, User user) throws SQLException {
        String Sql = "SELECT * From user WHERE userName = ?";
        PreparedStatement pstmt = con.prepareStatement(Sql);
        pstmt.setString(1, user.getUserName());

        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }
}
