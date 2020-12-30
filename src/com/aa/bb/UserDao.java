package com.aa.bb;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    User login(Connection con, User user) throws SQLException;

    int register(Connection con, User user) throws SQLException;

    boolean checkUser(Connection con, User user) throws SQLException;
}
