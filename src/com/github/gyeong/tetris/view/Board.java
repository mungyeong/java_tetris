package com.github.gyeong.tetris.view;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private Main mi;
    private JButton btn_menu = new JButton("메뉴");

    public Board(Main mi) {
        this.mi = mi;
        Tetris t = new Tetris(400,800);
        t.setBounds(0,0,400,800);
        add(t);
        setBounds(0,0,600,800);
        btn_menu.setBounds(550,0,50,50);
        add(btn_menu);
        setLayout(null);
        setBackground(Color.BLACK);
        setVisible(true);
        getRootPane();
    }

    public JButton getBtn_menu() {
        return btn_menu;
    }
}
