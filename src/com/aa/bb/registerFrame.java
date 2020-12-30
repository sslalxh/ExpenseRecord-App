package com.aa.bb;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class registerFrame extends JFrame {
    private static final int Default_width = 300;
    private static final int Default_height = 300;

    private final dbConnect dbcon = new dbConnect();
    private final UserDaoInt userDao = new UserDaoInt();

    JTextField userField;
    JPasswordField passwordField, repeatField;
    JLabel userLabel, passwordLabel,repeatLabel, welLabel;
    JPanel row1, row2, row3, row4, row5;
    JButton confirmButton, exitButton;

    public registerFrame(){
        setLayout(new GridLayout(5,1));

        welLabel = new JLabel("注册");
        Font welFont = new Font("宋体", Font.BOLD, 16);
        welLabel.setFont(welFont);
        welLabel.setForeground(Color.BLACK);
        row1 = new JPanel();
        row1.add(welLabel);

        userLabel = new JLabel("注册用户名:");
        userField = new JTextField(10);
        userField.setBackground(Color.PINK);
        row2 = new JPanel();
        row2.add(userLabel);
        row2.add(userField);

        passwordLabel = new JLabel("密            码:");
        passwordField = new JPasswordField(10);
        passwordField.setBackground(Color.PINK);
        row3 = new JPanel();
        row3.add(passwordLabel);
        row3.add(passwordField);

        repeatLabel = new JLabel("重复    密码");
        repeatField = new JPasswordField(10);
        repeatField.setBackground(Color.PINK);
        row4 = new JPanel();
        row4.add(repeatLabel);
        row4.add(repeatField);

        confirmButton = new JButton("确定");
        exitButton = new JButton("取消");
        row5 = new JPanel();
        row5.add(confirmButton);
        row5.add(exitButton);

        confirmButton.addActionListener(event ->
        {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());
            String repeat = new String(repeatField.getPassword());

            Connection con = dbcon.getConnect();
            User registerUser = new User(username, password);

            if (username == null || username.trim().equals("")){
                JOptionPane.showMessageDialog(null, "用户名不能为空");
                return;
            }
            try{
                if (userDao.checkUser(con, registerUser)){
                    JOptionPane.showMessageDialog(null, "用户名已被注册");
                    return;
                }
                if (password.trim().equals("")){
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                    return;
                }
                if (! password.equals(repeat)){
                    JOptionPane.showMessageDialog(null, "两次输入的密码不相同");
                    return;
                }

                int getRow = userDao.register(con, registerUser);
                if (getRow != 0){
                    JOptionPane.showMessageDialog(null, "账户注册成功");
                    dispose();
                }
            }catch (SQLException e){
                 e.printStackTrace();
            }finally {
                dbcon.closeConnect(con);
            }
        });

        exitButton.addActionListener(event -> dispose());

        add(row1);
        add(row2);
        add(row3);
        add(row4);
        add(row5);

        setSize(Default_width, Default_height);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
