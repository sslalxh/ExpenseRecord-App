package com.aa.bb;

import java.sql.Date;

/**
 * expenseItem实体类
 */
public class ExItem {
    private Date date;
    private String item;
    private int expense;

    public ExItem() {
    }

    /**
     * 构造方法
     * @param date 日期
     * @param item 项目
     * @param expense 消费金额
     */
    public ExItem(Date date, String item, int expense) {
        this.date = date;
        this.item = item;
        this.expense = expense;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }
}
