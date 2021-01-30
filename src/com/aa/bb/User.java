package com.aa.bb;

/**
 * User实体类
 */
public class User {
    private int id;
    private String userName;
    private String password;

    //各种get/set方法
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 构造方法
     * @param userName 用户名
     * @param password 密码
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }
}
