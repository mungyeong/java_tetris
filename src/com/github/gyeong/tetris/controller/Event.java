package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.view.Main;

import javax.swing.*;

public class Event {

    private Main mi;

    public Event(Main mi) {
        this.mi = mi;
        eventSet();
    }

    private void eventSet() {
        mi.getBtn_board().addActionListener(e -> click_event(0));
        mi.getBtn_score().addActionListener(e -> click_event(1));
        mi.getBtn_exit().addActionListener(e -> System.exit(0));
        mi.getBd().getBtn_menu().addActionListener(e -> click_event(2));
        mi.getBd().getBtn_game().addActionListener(e -> {
            JButton m = mi.getBd().getBtn_game();
            Tetris t = mi.getBd().getT();
            if(m.getText().equals("중지")){
                t.getTetrisTimer().stop();
            }
            else {
                t.start();
                t.resume();
            }
            m.setText(m.getText().equals("중지")?"시작":"중지");
        });
        mi.getSb().getBtn_menu().addActionListener(e -> click_event(2));
    }

    public void click_event(int index) {
        if (index < 3) {
            change(index);
        }
    }

    public void change(int name) {
        if (name == 0) {
            mi.getContentPane().removeAll();
            mi.getContentPane().add(mi.getBd());
            mi.revalidate();
            mi.repaint();
        } else if (name == 1) {
            mi.getContentPane().removeAll();
            mi.getContentPane().add(mi.getSb());
            mi.revalidate();
            mi.repaint();
        } else if (name == 2) {
            mi.getContentPane().removeAll();
            btn_add();
            mi.revalidate();
            mi.repaint();
        }
    }

    public void btn_add() {
        mi.getBtn_board().setBounds(150, 200, 300, 100);
        mi.getBtn_score().setBounds(150, 350, 300, 100);
        mi.getBtn_exit().setBounds(150, 500, 300, 100);
        mi.add(mi.getBtn_board());
        mi.add(mi.getBtn_score());
        mi.add(mi.getBtn_exit());
    }
}
