package com.aa.bb;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 登录窗口
 */
public class loginFrame extends JFrame {
    /**
     * 窗口大小
     */
    private static final int Default_width = 300;
    private static final int Default_height = 300;

    //获取数据库连接类及用户Dao类对象
    private final dbConnect dbcon = new dbConnect();
    private final UserDaoInt userDao = new UserDaoInt();

    //各种组件
    JTextField userField;
    JPasswordField passwordField;
    JLabel userLabel, passwordLabel, welLabel;
    JButton logButton,registerButton ,exitButton;
    JPanel row1, row2, row3, row4;

    /**
     * loginFrame构造方法
     */
    public loginFrame(){
        setLayout(new GridLayout(4,1));                         //设置布局

        welLabel = new JLabel("欢迎使用");                             //窗口标签
        Font welFont = new Font("宋体", Font.BOLD, 16);         //新建窗口标签字体
        welLabel.setFont(welFont);                                         //设置窗口标签字体
        welLabel.setForeground(Color.BLUE);                                //设置窗口标签字体颜色
        row1 = new JPanel();                                               //新建面版
        row1.add(welLabel);                                                //在面板中添加标签

        userLabel = new JLabel("用户名:");
        userField = new JTextField(10);
        row2 = new JPanel();
        row2.add(userLabel);
        row2.add(userField);

        passwordLabel = new JLabel("密    码:");
        passwordField = new JPasswordField(10);
        row3 = new JPanel();
        row3.add(passwordLabel);
        row3.add(passwordField);

        logButton = new JButton("登录");
        registerButton = new JButton("注册");
        exitButton = new JButton("取消");
        row4 = new JPanel();
        row4.add(logButton);
        row4.add(registerButton);
        row4.add(exitButton);

        //添加logButton监听器，设置登录动作
        logButton.addActionListener(event ->
        {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());

            if (username == null || username.trim().equals("")){ //判断用户名框是否为空
                JOptionPane.showMessageDialog(null, "用户名不能为空");
                return;
            }
            if (password.trim().equals("")){ //判断密码框是否为空
                JOptionPane.showMessageDialog(null, "密码不能为空");
                return;
            }

            Connection con = null;
            User user = new User(username, password); //实例User实体类
            try {
                con = dbcon.getConnect();
                User outUser = userDao.login(con, user);
                if (outUser != null){  //用户名密码匹配，则登录成功，进入主窗口
                    dispose();
                    new mainFrame();
                }
                else {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误");  //不匹配，则弹出错误提示框
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                dbcon.closeConnect(con);  //关闭数据库连接
            }

        });

        registerButton.addActionListener(event -> new registerFrame()); //添加registerButton监听器，弹出注册窗口

        exitButton.addActionListener(event -> System.exit(0));  //添加exitButton监听器，退出程序

        add(row1);
        add(row2);
        add(row3);
        add(row4);
        //设置窗口信息：标题，居中显示，默认退出操作
        setTitle("个人记账系统");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Default_width,Default_height);
        setLocation((screenSize.width - Default_width) / 2,(screenSize.height - Default_height) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

