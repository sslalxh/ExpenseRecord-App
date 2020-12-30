package com.aa.bb;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;


public class loginFrame extends JFrame {

    private static final int Default_width = 300;
    private static final int Default_height = 300;

    private final dbConnect dbcon = new dbConnect();
    private final UserDaoInt userDao = new UserDaoInt();

    JTextField userField;
    JPasswordField passwordField;
    JLabel userLabel, passwordLabel, welLabel;
    JButton logButton,registerButton ,exitButton;
    JPanel row1, row2, row3, row4;

    public loginFrame(){
        setLayout(new GridLayout(4,1));

        welLabel = new JLabel("欢迎使用");
        Font welFont = new Font("宋体", Font.BOLD, 16);
        welLabel.setFont(welFont);
        welLabel.setForeground(Color.BLUE);
        row1 = new JPanel();
        row1.add(welLabel);

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

        logButton.addActionListener(event ->
        {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());

            if (username == null || username.trim().equals("")){
                JOptionPane.showMessageDialog(null, "用户名不能为空");
                return;
            }
            if (password.trim().equals("")){
                JOptionPane.showMessageDialog(null, "密码不能为空");
                return;
            }

            Connection con = null;
            User user = new User(username, password);
            try {
                con = dbcon.getConnect();
                User outUser = userDao.login(con, user);
                if (outUser != null){
                    dispose();
                    var mainFrame = new mainFrame();
                }
                else {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                dbcon.closeConnect(con);
            }

        });

        registerButton.addActionListener(event -> new registerFrame());

        exitButton.addActionListener(event -> System.exit(0));

        add(row1);
        add(row2);
        add(row3);
        add(row4);

        setTitle("消费记录系统");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Default_width,Default_height);
        setLocation((screenSize.width - Default_width) / 2,(screenSize.height - Default_height) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        var frame = new loginFrame();
    }
}

