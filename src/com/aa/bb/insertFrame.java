package com.aa.bb;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 插入窗口
 */
public class insertFrame extends JFrame {
    /**
     * 窗口大小
     */
    public static final int Default_width = 400;
    public static final int Default_height = 400;

    private final dbConnect dbcon = new dbConnect();
    private final ExItemDaoInt exitemDao = new ExItemDaoInt();

    //各种组件
    JLabel welLabel, dateLabel, itemLabel, exLabel;
    JTextField dateField, itemField, exField;
    JButton confirmButton, exitButton;
    JPanel row1, row2, row3, row4, row5;

    /**
     * 构造方法
     */
    public insertFrame(){
        setLayout(new GridLayout(5,1));   //设置窗口布局

        //设置两个字体
        Font font = new Font("宋体", Font.BOLD, 24);
        Font font1 = new Font("仿宋", Font.BOLD, 18);

        welLabel = new JLabel("记账");
        welLabel.setFont(font);
        welLabel.setForeground(Color.BLUE);
        row1 = new JPanel();
        row1.add(welLabel);

        dateLabel = new JLabel("收支日期");
        dateLabel.setFont(font1);
        dateField = new JTextField("格式要求:xxxx-xx-xx",10);
        row2 = new JPanel();
        row2.add(dateLabel);
        row2.add(dateField);

        itemLabel = new JLabel("项    目");
        itemLabel.setFont(font1);
        itemField = new JTextField("默认",10);
        row3 = new JPanel();
        row3.add(itemLabel);
        row3.add(itemField);

        exLabel = new JLabel("金    额");
        exLabel.setFont(font1);
        exField = new JTextField(10);
        row4 = new JPanel();
        row4.add(exLabel);
        row4.add(exField);

        confirmButton = new JButton("确认");
        //添加confirmButton监听器，设置记账动作
        confirmButton.addActionListener(event ->
        {
            SimpleDateFormat DatFormat = new SimpleDateFormat("yyyy-MM-dd"); //创建日期格式
            String dateStringToParse = dateField.getText(); //获得输入的日期字符串
            String item = itemField.getText(); //获得输入的项目字符串
            String expenseToParse = exField.getText(); //获得输入的消费金额字符串

            if (dateStringToParse == null || dateStringToParse.trim().equals("")){
                JOptionPane.showMessageDialog(null, "支出日期不能为空");
                return;
            }

            if (expenseToParse == null || expenseToParse.trim().equals("")){
                JOptionPane.showMessageDialog(null, "支出金额不能为空");
                return;
            }

            Connection con = dbcon.getConnect();
            try{
                int expense = Integer.parseInt(expenseToParse); //由消费金额字符串解析得到消费金额整型变量

                java.util.Date date = DatFormat.parse(dateStringToParse); //日期字符串解析得到Date数据类型变量
                java.sql.Date sqlDate = new java.sql.Date(date.getTime()); //得到sql.Date数据

                ExItem exItem = new ExItem(sqlDate, item, expense);
                int getRow =exitemDao.insert(con, exItem);
                if (getRow != 0){
                    JOptionPane.showMessageDialog(null, "记账成功");
                }
            }catch (ParseException e){
                JOptionPane.showMessageDialog(null, "日期格式错误");
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        });

        exitButton = new JButton("取消");
        exitButton.addActionListener(event -> dispose());
        row5 = new JPanel();
        row5.add(confirmButton);
        row5.add(exitButton);

        add(row1);
        add(row2);
        add(row3);
        add(row4);
        add(row5);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Default_width,Default_height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation((screenSize.width - Default_width) / 2,(screenSize.height - Default_height) / 2);
        setVisible(true);
    }
}
