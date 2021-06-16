package com.github.gyeong.tetris.view.support;

import javax.swing.*;
import java.awt.*;

public class Score extends JLabel {

    private static Score score;

    public static Score getInstance() {
        if(score == null){
            score = new Score();
        }
        return score;
    }

    private Score() {
        setBounds(400, 300, 200, 200);
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        Font font = new Font("바탕", Font.ITALIC, 30);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(font);
    }

    public void init() {
        setText("0");
    }

    public void update_Score(int score) {
        setText(score);
    }

    private void setText(int current_score) {
        super.setText(String.valueOf(current_score));
    }
}
