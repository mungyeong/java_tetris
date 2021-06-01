package com.github.gyeong.tetris.view;

import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel {

    private Main mi;

    private JButton btn_board = new JButton("메뉴");

    public Scoreboard(Main mi) {
        this.mi = mi;
        setBounds(0,0,600,800);
        btn_board.setBounds(550,0,50,50);
        btn_board.addActionListener(e -> mi.change(2));
        add(btn_board);
        setLayout(null);
        setBackground(Color.GRAY);
        setVisible(true);
        getRootPane();
    }
}
