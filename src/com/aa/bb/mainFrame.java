package com.aa.bb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {    public static final int Default_width = 800;
    public static final int Default_height = 600;

    JMenuBar menuBar;
    JMenu startMenu, ioMenu, helpMenu;
    AbstractAction recordAc, selectAc, deleteAc;
    JLabel bgLabel;

    //主窗口构造方法
    public mainFrame(){
        //向主窗口插入背景图片
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/IMG333.jpg"));
        bgLabel = new JLabel(icon);
        add(bgLabel);

        //建立"开始"菜单
        startMenu = new JMenu("开始");
        startMenu.setMnemonic('S');

        recordAc = new optionAction("记账", "创建一项消费或收入记录") {    //构造"记账"按钮
            @Override
            public void actionPerformed(ActionEvent e) {
                new insertFrame();                                                      //弹出插入界面
            }
        };
        JMenuItem recordItem = new JMenuItem(recordAc);
        recordItem.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));                    //为菜单项添加键盘快捷键
        startMenu.add(recordItem);

        selectAc = new optionAction("查询", "查询消费或收入记录") {       //构造"查询"按钮
            @Override
            public void actionPerformed(ActionEvent e) {
                new selectFrame();
            }
        };
        JMenuItem selectItem = new JMenuItem(selectAc);
        selectItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        startMenu.add(selectItem);

        deleteAc = new optionAction("删除", "删除消费或收入记录") {        //构造"删除"按钮
            @Override
            public void actionPerformed(ActionEvent e) {
                //待完成
            }
        };
        JMenuItem deleteItem =new JMenuItem(deleteAc);
        deleteItem.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
        startMenu.add(deleteItem);

        //建立"导入/导出"菜单 及 "帮助"菜单
        ioMenu = new JMenu("导入/导出");
        helpMenu = new JMenu("帮助");

        //添加菜单栏
        menuBar = new JMenuBar();
        menuBar.add(startMenu);
        menuBar.add(ioMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        //设置窗口标题,大小,位置,关闭时的默认操作等信息
        setTitle("个人记账系统");
        setSize(Default_width, Default_height);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Default_width, Default_height);
        setLocation((screenSize.width - Default_width) / 2, (screenSize.height - Default_height) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //构造操作动作抽象类,抽象继承AbstractAction类,用于构造菜单按键
    abstract static class optionAction extends AbstractAction{
        public optionAction(String name, String description){
            putValue(Action.NAME, name);
            putValue(Action.SHORT_DESCRIPTION, description);
        }

        public optionAction(String name){
            putValue(Action.NAME, name);
        }

        public abstract void actionPerformed(ActionEvent e);
    }
}
