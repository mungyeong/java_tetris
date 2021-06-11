package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.support.Event;

import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel {

    private static Scoreboard scoreboard;

    public static Scoreboard getInstance() {

        if (scoreboard == null) {
            scoreboard = new Scoreboard();
        }
        return scoreboard;
    }

    private Main main;

    private JButton btn_menu = new JButton("메뉴");

    private ScoreTable table = new ScoreTable();

    private JScrollPane jScrollPane = new JScrollPane();

    private Scoreboard() {
        this.main = Main.getInstance();
        setBounds(0, 0, 600, 825);
        jScrollPane.setBounds(50,100,500,600);
        add(jScrollPane.add(table));
        add(btn_menu).setBounds(550, 0, 50, 50);
        btn_menu.addActionListener(e -> {
            Event.change(2);
        });
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }

    public void init() {
    }

    public void update() {
        table.reFresh();
    }
}
