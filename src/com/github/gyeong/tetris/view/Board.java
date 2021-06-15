package com.github.gyeong.tetris.view;


import com.github.gyeong.tetris.support.Event;
import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.support.Score;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private static Board board;

    private Main main = Main.getInstance();

    private Score score;

    private Tetris tetris;

    private Preview preview;

    private JButton btn[] = {
            new JButton("메뉴"),
            new JButton("시작"),
            new JButton("재도전")
    };

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    private Board() {
        score = Score.getInstance();
        tetris = new Tetris(400, 800);
        preview = new Preview(tetris);
        init();
        add(score);
        add(tetris);
        add(preview);
        event_Set();
        setBounds(0, 0, 600, 800);
        setLayout(null);
        setBackground(Color.BLACK);
        setVisible(true);
        getRootPane();
    }

    public void event_Set() {
        for (int i = 0; i < btn.length; i++) {
            int index = i,x,y,w=50,h=50;
            btn[i].addActionListener(e -> {
                event(index);
            });
            if(i == 0){
                x = 550;
                y = 0;
            } else {
                x = 450 + (50 * i);
                y = 700;
            }
            add(btn[i]).setBounds(x,y,w, h);
        }
    }

    public void event(int index) {
        if (index == 0) {
            tetris.init();
            Event.change(2);
        }
        if (index == 1) {
            switch (tetris.getState().getState()) {
                case 3:
                case 0:
                    tetris.start();
                    break;
                case 1:
                    tetris.pause();
                    break;
                case 2:
                    tetris.resume();
                    break;
            }
            btn[1].setText(btn[1].getText() != "시작" ? "시작" : "중지");
        }
        if (index == 2) {
            if (JOptionPane.showConfirmDialog(main, "재도전하시겠습니까?","재도전", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                tetris.stop();
                Btn_init();
                preview.init();
            }
        }
        tetris.requestFocus();
    }

    public void init() {
        Btn_init();
        score.init();
        preview.init();
        tetris.init();
    }

    public void update() {
        score.update_Score(tetris.getScore().getIntScore());
        preview.update(tetris.getNext());
    }

    public void Btn_init() {
        btn[1].setText("시작");
    }

}
