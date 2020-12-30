package com.aa.bb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {
    public static final int Default_width = 800;
    public static final int Default_height = 500;

    JMenuBar menuBar;
    JMenu startMenu, ioMenu, helpMenu;
    AbstractAction recordAc, selectAC, delcectAc;

    public mainFrame(){
        startMenu = new JMenu("开始");

        recordAc = new optionAction("记账", "创建一项消费记录") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("selected");
            }
        };
        startMenu.add(recordAc);

        selectAC = new optionAction("查询记录", "查询消费记录") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        startMenu.add(selectAC);

        delcectAc = new optionAction("删除记录", "删除消费记录") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        startMenu.add(delcectAc);


        ioMenu = new JMenu("导入/导出");
        helpMenu = new JMenu("帮助");








        menuBar = new JMenuBar();
        menuBar.add(startMenu);
        menuBar.add(ioMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
        setSize(Default_width, Default_height);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Default_width, Default_height);
        setLocation((screenSize.width - Default_width) / 2, (screenSize.height - Default_height) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    abstract static class optionAction extends AbstractAction{
        public optionAction(String name, String description){
            putValue(Action.NAME, name);
            putValue(Action.SHORT_DESCRIPTION, description);
        }

        public abstract void actionPerformed(ActionEvent e);
    }


    public static void main(String[] args) {
        var frame = new mainFrame();
    }
}
