package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.view.Main;

import java.awt.*;

public class Event {
    private static Main main = Main.getInstance();

    public static void change(int index) {
        Container main_Temp = main.getContentPane();
        main_Temp.removeAll();
        if (index == 0) {
            main_Temp.add(main.getBd());
        }
        if (index == 1) {
            main_Temp.add(main.getSb());
        }
        if (index == 2) {
            main.eventSet();
        }
        main.revalidate();
        main.repaint();
    }

    public static void change_Main(int index) {
        if (index==2) {
            System.exit(0);
        }
        change(index);
    }
}
