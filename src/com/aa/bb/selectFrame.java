package com.aa.bb;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class selectFrame extends JFrame {
    public static final int Default_width = 500;
    public static final int Default_height = 400;

    private final dbConnect dbcon = new dbConnect();
    private final ExItemDaoInt exItemDao = new ExItemDaoInt();

    JLabel welLabel, dateLabel;                  //“项目”，“金额”查询待添加
    JTextField dateField;
    JButton confirmButton, exitButton;
    JPanel part1, part2;
    JTable table;
    String[] columnName = {"日期", "项目", "金额"};

    public selectFrame(){
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.CENTER;
        setLayout(gridBag);
        constraints.fill = GridBagConstraints.NONE;
        //constraints.weightx = 1;
        //constraints.weighty = 1;
        Font welFont = new Font("宋体", Font.BOLD, 20);
        welLabel = new JLabel("查询");
        welLabel.setFont(welFont);
        add(welLabel, constraints, 0, 0, 4,1);

        dateLabel = new JLabel("查询日期");
        dateField = new JTextField(10);
        part1 = new JPanel();
        part1.add(dateLabel);
        part1.add(dateField);
        add(part1, constraints, 0,1, 4, 1);

        confirmButton = new JButton("查询");
        exitButton = new JButton("取消");
        part2 = new JPanel();
        part2.add(confirmButton);
        part2.add(exitButton);
        add(part2, constraints, 0, 3, 4, 1);

        exitButton.addActionListener(event -> dispose());
        confirmButton.addActionListener(event -> {                                            //查询操作尚未完成
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStringToParse = dateField.getText();
            Object[][] rowData = new Object[10][3];

            Connection con = dbcon.getConnect();
            try{
                java.util.Date data = simpleDateFormat.parse(dateStringToParse);
                java.sql.Date sqlDate = new java.sql.Date(data.getTime());

                rowData = exItemDao.select(con, sqlDate);
            }catch (ParseException | SQLException e){
                System.out.println(e.getMessage());
            }

            table = new JTable(rowData, columnName);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, constraints, 0, 4, 4, 4);
        });


        table = new JTable(4,3);
        add(table, constraints, 0, 4, 4, 4);


        setSize(Default_width, Default_height);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Default_width, Default_height);
        setLocation((screenSize.width - Default_width) / 2, (screenSize.height - Default_height) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void add(Component component, GridBagConstraints constraints, int x, int y, int width, int height){
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        add(component, constraints);
    }
}
