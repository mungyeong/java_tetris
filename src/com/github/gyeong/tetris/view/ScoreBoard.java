package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.view.support.Event;
import com.github.gyeong.tetris.view.support.FileSteam;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {

    private static ScoreBoard scoreboard;

    public static ScoreBoard getInstance() {
        if (scoreboard == null) {
            scoreboard = new ScoreBoard();
        }
        return scoreboard;
    }

    private Main main;

    private JButton btn_menu = new JButton("메뉴");

    private ScoreTable table = new ScoreTable();

    private JScrollPane jScrollPane = new JScrollPane(table);

    private JButton btn_delete = new JButton("삭제");

    private ScoreBoard() {
        this.main = Main.getInstance();
        setBounds(0, 0, 600, 825);
        jScrollPane.setBounds(50, 100, 500, 600);
        setBackground(new Color(56, 61, 63));
        add(jScrollPane);
        add(btn_menu).setBounds(550, 0, 50, 50);
        add(btn_delete).setBounds(550, 700, 50, 50);
        btn_menu.addActionListener(e -> {
            Event.change(2);
        });
        btn_delete.addActionListener(e -> {
            removeScore(table.getSelectedRows());
        });
        setLayout(null);
        setBackground(Color.GRAY);
        setVisible(true);
    }

    public void update() {
        table.reFresh();
    }

    public void removeScore(int[] indexs) {
        FileSteam fileSteam = FileSteam.getInstance();
        for (int index: indexs){
            fileSteam.remove(index);
        }
        table.reFresh();
    }
}
