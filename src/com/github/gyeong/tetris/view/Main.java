package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.controller.Event;

import javax.swing.*;

public class Main extends JFrame {

    private static Main mi = new Main();

    private GameBoard bd = new GameBoard(this);
    private Scoreboard sb = new Scoreboard(this);
    private JButton btn_board = new JButton("게임 시작");
    private JButton btn_score = new JButton("점수판");
    private JButton btn_exit = new JButton("게임 종료");



    private Event be = new Event(this);

    public static Main getInstance() {
        return mi;
    }


    private Main() {
        be.btn_add();
        setTitle("자바 테트리스 게임");
        setSize(600, 850);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public GameBoard getBd() {
        return bd;
    }

    public Scoreboard getSb() {
        return sb;
    }

    public JButton getBtn_board() {
        return btn_board;
    }

    public JButton getBtn_score() {
        return btn_score;
    }

    public JButton getBtn_exit() {
        return btn_exit;
    }

    public static void main(String[] args) {
        Main mi = Main.getInstance();
    }
}
