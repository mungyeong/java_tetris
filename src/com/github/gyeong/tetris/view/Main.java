package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.controller.Event;

import javax.swing.*;

public class Main extends JFrame {

    private static Main main = new Main();

    public static Main getInstance() {
        return main;
    }

    private GameBoard bd = GameBoard.getInstance();

    private Scoreboard sb = Scoreboard.getInstance();

    private JButton btn[] = {
            new JButton("게임 시작"),
            new JButton("점수판"),
            new JButton("게임 종료")
    };


    private Main() {
        setTitle("자바 테트리스 게임");
        setSize(600, 850);
        eventSet();
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void eventSet() {
        for (int i = 0; btn.length > i; i++) {
            int index = i;
            add(btn[i]).setBounds(150, 200 + (i * 150), 300, 100);
            btn[i].addActionListener(e -> Event.change_Main(index));
        }
    }

    public GameBoard getBd() {
        return bd;
    }

    public Scoreboard getSb() {
        return sb;
    }

    public static void main(String[] args) {
        Main main = Main.getInstance();
    }

}
