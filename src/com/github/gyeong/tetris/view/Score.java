package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.controller.Tetris;

import javax.swing.*;
import java.awt.*;

public class Score extends JTextPane {

    private GameBoard gameBoard;

    private Integer score;

    private Tetris t;

    public Score(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        setBounds(400, 300, 200, 200);
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        Font font = new Font("바탕", Font.ITALIC, 30);
        setFont(font);
    }

    public void init() {
        t = gameBoard.getT();
        this.score = 0;
        setText(this.score.toString());
    }

    public void update() {
        this.score = t.getGame_user_score().getUserScore();
        setText(this.score.toString());
    }
}
