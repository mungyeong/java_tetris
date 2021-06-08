package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.view.Main;

import javax.swing.*;

public class Event {

    private Main main;

    public Event(Main main) {
        this.main = main;
        eventSet();
    }

    private void eventSet() {
        Tetris tetris = main.getBd().getT();
        main.getBtn_board().addActionListener(e -> change(0));
        main.getBtn_score().addActionListener(e -> change(1));
        main.getBtn_exit().addActionListener(e -> System.exit(0));
        main.getBd().getBtn_menu().addActionListener(e -> {
            change(2);
            tetris.stop();
        });
        main.getBd().getBtn_game().addActionListener(e -> {
            JButton m = main.getBd().getBtn_game();
            switch (tetris.getGame_state().getState()) {
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
            tetris.requestFocus();
            m.setText(m.getText() != "시작" ? "시작" : "중지");

        });
        main.getBd().getBtn_stop().addActionListener(e -> {
            JOptionPane j = new JOptionPane();
            if (j.showConfirmDialog(main, "재도전하시겠습니까?", "재도전", j.YES_NO_OPTION, j.WARNING_MESSAGE) == 0) {
                main.getBd().getBtn_game().setText("시작");
                tetris.stop();
            }
            tetris.requestFocus();
        });
        main.getSb().getBtn_menu().addActionListener(e -> change(2));
    }


    private void change(int index) {
        main.getContentPane().removeAll();
        if (index == 0) {
            main.getContentPane().add(main.getBd());
        }
        if (index == 1) {
            main.getContentPane().add(main.getSb());
        }
        if (index == 2) {

            btn_add();
        }
        main.revalidate();
        main.repaint();
    }

    public void btn_add() {
        main.getBtn_board().setBounds(150, 200, 300, 100);
        main.getBtn_score().setBounds(150, 350, 300, 100);
        main.getBtn_exit().setBounds(150, 500, 300, 100);
        main.add(main.getBtn_board());
        main.add(main.getBtn_score());
        main.add(main.getBtn_exit());
    }
}
