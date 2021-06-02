package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.util.ButtonEvent;

import javax.swing.*;

public class Main extends JFrame {

    private Board bd = new Board(this);
    private Scoreboard sb = new Scoreboard(this);
    private ButtonEvent be = new ButtonEvent(this);
    private JButton btn_board = new JButton("게임 시작");
    private JButton btn_score = new JButton("점수판");
    private JButton btn_exit = new JButton("게임 종료");


    public Main() {
        be.btn_add();
        setTitle("자바 테트리스 게임");
        setSize(600, 850);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public Board getBd() {
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

    public ButtonEvent getBe() {
        return be;
    }

    public static void main(String[] args) {
        Main mi = new Main();
    }
}
