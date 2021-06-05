package com.github.gyeong.tetris.view;


import com.github.gyeong.tetris.controller.Tetris;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private Main mi;
    private Tetris t;
    private JButton btn_menu = new JButton("메뉴");
    private JButton btn_game = new JButton("시작");
    private JButton btn_stop = new JButton("정지");


    public GameBoard(Main mi) {
        this.mi = mi;
        t = new Tetris(400, 800);
        t.setBounds(0, 0, 400, 800);
        add(t);
        setBounds(0, 0, 600, 800);
        btn_menu.setBounds(550, 0, 50, 50);
        btn_game.setBounds(450, 700, 60, 100);
        btn_stop.setBounds(450, 700, 100, 100);
        add(btn_menu);
        add(btn_game);
        setLayout(null);
        setBackground(Color.BLACK);
        setVisible(true);
        getRootPane();
    }

    public Tetris getT() {
        return t;
    }

    public JButton getBtn_menu() {
        return btn_menu;
    }

    public JButton getBtn_game() {
        return btn_game;
    }

}
