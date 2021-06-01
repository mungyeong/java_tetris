package com.github.gyeong.tetris.view;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JPanel {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT= 20;
    private final int BOARD_INTERVAL= 300;

    private int board[][];

    private int WIDHT = 0;
    private int HEIGHT = 0;

    public Tetris(int w, int h) {
        this.WIDHT = w;
        this.HEIGHT = h;

        board = new int[this.BOARD_HEIGHT][this.BOARD_WIDTH];
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.PINK);
        g.fillRect(0,0,40,40);
        g.setColor(Color.BLACK);
        for (int w = 0; w <= WIDHT; w+=WIDHT/BOARD_WIDTH) {
            g.drawLine(w,0,w,HEIGHT);
        }
        for (int h = 0; h <= HEIGHT; h+=HEIGHT/BOARD_HEIGHT) {
            g.drawLine(0,h,WIDHT,h);
        }

    }
    public void init() {
        for (int i = 0; i < this.getBOARD_HEIGHT(); i++) {
            for (int j = 0; j < this.getBOARD_WIDTH(); j++) {
                board[i][j] = 0;
            }
        }
    }

    public int getBOARD_WIDTH() {
        return BOARD_WIDTH;
    }

    public int getBOARD_HEIGHT() {
        return BOARD_HEIGHT;
    }

    public int getBOARD_INTERVAL() {
        return BOARD_INTERVAL;
    }
}
