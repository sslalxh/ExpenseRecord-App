package com.aa.bb;

import java.sql.*;

public class ExItemDaoInt implements ExItemDao {
    @Override
    public int insert(Connection con, ExItem exitem) throws SQLException {
        String Sql = "INSERT INTO expense_item (Date, Item, Expense) VALUES (?, ?, ?)"; //sql语句
        PreparedStatement pstmt = con.prepareStatement(Sql);  //获取预编译的数据库操作对象
        pstmt.setDate(1, exitem.getDate());  //设置指定位置的参数
        pstmt.setString(2, exitem.getItem());
        pstmt.setInt(3, exitem.getExpense());

        return pstmt.executeUpdate();
    }

    @Override
    public Object[][] select(Connection con, Date date) throws SQLException {  //尚未完成
        int count = 0;
        Object[][] rowData = new Object[10][3];
        String Sql = "SELECT * FROM expense_item WHERE Date = ?";
        PreparedStatement pstmt = con.prepareStatement(Sql);
        pstmt.setDate(1, date);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            rowData[count][0] = rs.getDate("Date");
            rowData[count][1] = rs.getString("Item");
            rowData[count][2] = rs.getInt("Expense");
            count++;
        }
        return rowData;
    }
}
