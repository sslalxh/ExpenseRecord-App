package com.aa.bb;

import java.sql.*;

public class UserDaoInt implements UserDao {

    @Override
    public User login(Connection con, User user) throws SQLException {
        User outUser = null;
        String Sql = "SELECT * FROM user WHERE userName = ? AND password = ?";
        PreparedStatement pstmt = con.prepareStatement(Sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());

        ResultSet rs = pstmt.executeQuery();
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

        return pstmt.executeUpdate();
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
