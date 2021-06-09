package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.controller.Event;
import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel {

    private static Scoreboard scoreboard = new Scoreboard();

    public static Scoreboard getInstance() {
        return scoreboard;
    }

    private Main main;

    private JButton btn_menu = new JButton("메뉴");

    private Scoreboard() {
        this.main = Main.getInstance();
        setBounds(0,0,600,800);
        add(btn_menu).setBounds(550,0,50,50);
        btn_menu.addActionListener(e -> {
            Event.change(2);
        });
        setLayout(null);
        setBackground(Color.GRAY);
        setVisible(true);
        getRootPane();
    }
}
