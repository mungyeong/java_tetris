package com.github.gyeong.tetris.view;


import com.github.gyeong.tetris.controller.Tetris;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private Main mi;
    private Tetris t;
    private JButton btn_menu = new JButton("메뉴");
    private JButton btn_game = new JButton("시작");
    private JButton btn_stop = new JButton("재도전");

    private Score score;


    public GameBoard(Main mi) {
        this.mi = mi;
        score = new Score(this);
        t = new Tetris(400, 800, this);
        score = new Score(this);
        add(score);
        add(t);
        setBounds(0, 0, 600, 800);
        btn_menu.setBounds(550, 0, 50, 50);
        btn_game.setBounds(450, 700, 50, 50);
        btn_stop.setBounds(500, 700, 50, 50);
        add(btn_menu);
        add(btn_game);
        add(btn_stop);
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

    public JButton getBtn_stop() {
        return btn_stop;
    }

    public Score getScore() {
        return score;
    }

}
