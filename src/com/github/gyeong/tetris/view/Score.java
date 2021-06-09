package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.controller.Tetris;

import javax.swing.*;
import java.awt.*;

public class Score extends JTextPane {

    private static Score score = new Score();

    public static Score getInstance() {
        return score;
    }

    private Integer current_score;

    private Tetris t;

    private Score() {
        setBounds(400, 300, 200, 200);
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        Font font = new Font("바탕", Font.ITALIC, 30);
        setFont(font);
    }

    public void init() {
        this.current_score = 0;
        setText(this.current_score.toString());
    }

    public void update(int score) {
        this.current_score = score;
        setText(this.current_score.toString());
    }
}
