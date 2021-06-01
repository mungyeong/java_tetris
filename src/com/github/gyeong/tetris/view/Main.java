package com.github.gyeong.tetris.view;

import javax.swing.*;

public class Main extends JFrame {
    private Board bd = new Board(this);
    private Scoreboard sb = new Scoreboard(this);

    private JButton btn_board = new JButton("게임 시작");
    private JButton btn_score = new JButton("점수판");
    private JButton btn_exit = new JButton("게임 종료");


    public Main() {
        eventSet();
        btn_add();
        setTitle("자바 테트리스 게임");
        setSize(600, 850);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void change(int name) {
        if (name == 0) {
            getContentPane().removeAll();
            getContentPane().add(bd);
            revalidate();
            repaint();
        } else if (name == 1) {
            getContentPane().removeAll();
            getContentPane().add(sb);
            revalidate();
            repaint();
        } else if (name == 2) {
            getContentPane().removeAll();
            btn_add();
            revalidate();
            repaint();
        }

    }

    private void click_event(int index) {
        if (index < 3) {
            change(index);
        }
    }

    private void eventSet() {
        btn_board.addActionListener(e -> click_event(0));
        btn_score.addActionListener(e -> click_event(1));
        btn_exit.addActionListener(e -> System.exit(0));
        btn_board.setBounds(150, 50, 300, 100);
        btn_score.setBounds(150, 200, 300, 100);
        btn_exit.setBounds(150, 350, 300, 100);
    }

    private void btn_add() {
        add(btn_board);
        add(btn_score);
        add(btn_exit);
    }


    public static void main(String[] args) {
        Main mi = new Main();
    }
}
