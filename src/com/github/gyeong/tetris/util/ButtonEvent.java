package com.github.gyeong.tetris.util;

import com.github.gyeong.tetris.view.Main;

public class ButtonEvent {

    Main mi;

    public ButtonEvent(Main mi) {
        this.mi = mi;
    }

    private void eventSet() {
        mi.getBtn_board().addActionListener(e -> click_event(0));
        mi.getBtn_score().addActionListener(e -> click_event(1));
        mi.getBtn_exit().addActionListener(e -> System.exit(0));
        mi.getBd().getBtn_board().addActionListener(e -> click_event(2));
        mi.getSb().getBtn_board().addActionListener(e -> click_event(2));
    }

    private void click_event(int index) {
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
        eventSet();
        mi.getBtn_board().setBounds(150, 50, 300, 100);
        mi.getBtn_score().setBounds(150, 200, 300, 100);
        mi.getBtn_exit().setBounds(150, 350, 300, 100);
        mi.add(mi.getBtn_board());
        mi.add(mi.getBtn_score());
        mi.add(mi.getBtn_exit());
    }
}
