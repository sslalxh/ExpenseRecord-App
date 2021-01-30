package com.aa.bb;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DAO接口，把对数据库的操作定义成抽象方法
 */
public interface UserDao {
    /**
     * 登录方法
     * @param con 数据库连接
     * @param user User实体类对象
     * @return 返回登录成功的User实体类对象
     * @throws SQLException 数据库异常
     */
    User login(Connection con, User user) throws SQLException;

    /**
     * 注册方法
     * @param con 数据库
     * @param user User实体类对象
     * @return 注册成功则返回注册序号，否则返回0
     * @throws SQLException 数据库异常
     */
    int register(Connection con, User user) throws SQLException;

    /**
     * 查询用户是否存在
     * @param con 数据库连接
     * @param user User实体类
     * @return 存在则返回true，不存在返回false
     * @throws SQLException 数据库异常
     */
    boolean checkUser(Connection con, User user) throws SQLException;
}
