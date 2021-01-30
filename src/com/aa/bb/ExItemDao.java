package com.aa.bb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public interface ExItemDao {
    /**
     * 记账方法
     * @param con 数据库连接
     * @param exitem expenseItem实体类对象
     * @return 记账成功则返回非零整数，否则返回0
     * @throws SQLException 数据库异常
     */
    int insert(Connection con, ExItem exitem) throws SQLException;

    /**
     * 查询账单方法（尚未完成）
     * @param con 数据库连接
     * @param date 查询的日期
     * @return
     * @throws SQLException 数据库异常
     */
    Object[][] select(Connection con, Date date) throws SQLException;
}
