package com.github.gyeong.tetris.view;


import com.github.gyeong.tetris.controller.Event;
import com.github.gyeong.tetris.controller.Tetris;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private static GameBoard gameBoard = new GameBoard();

    public static GameBoard getInstance() {
        return gameBoard;
    }

    private Main main = Main.getInstance();
    private Tetris tetris;
    private JButton btn[] = {
            new JButton("메뉴"),
            new JButton("시작"),
            new JButton("재도전")
    };

    private Score score;


    private GameBoard() {
        score = Score.getInstance();
        tetris = new Tetris(400, 800);
        add(score);
        add(tetris);
        setBounds(0, 0, 600, 800);
        event_Set();
        setLayout(null);
        setBackground(Color.BLACK);
        setVisible(true);
        getRootPane();
    }

    public void event_Set() {
        for(int i=0;i<btn.length;i++){
            int index = i;
            btn[i].addActionListener(e -> {
                event(index);
            });
            add(btn[i]).setBounds(450+(i*50), i!=0?700:0, 50, 50);

        }
    }

    public void event(int index) {
        JButton btn_temp = btn[index];
        if(index == 0) {
            Event.change(2);
        }
        if (index == 1){
            switch (tetris.getState().getState()) {
                case 0:
                case 3:
                    tetris.start();
                    break;
                case 1:
                    tetris.pause();
                    break;
                case 2:
                    tetris.resume();
                    break;
            }
            btn_temp.setText(btn_temp.getText() != "시작" ? "시작" : "중지");
        }
        if(index == 2 ) {
            if (JOptionPane.showConfirmDialog(main, "재도전하시겠습니까?", "재도전",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                btn_temp.setText("시작");
                tetris.stop();
            }
        }
        tetris.requestFocus();
    }

    public void update_Score(int score) {
        this.score.update(score);
    }

}
