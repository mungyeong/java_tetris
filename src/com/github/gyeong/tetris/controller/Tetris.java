package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.model.*;
import com.github.gyeong.tetris.util.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.BLACK;

public class Tetris extends JPanel implements ActionListener {

    private Graphics graphicsBuffer = null;

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private int WIDHT = 0, HEIGHT = 0;;

    private int gameSpeed = 0;

    private IGamePlays game_plays = new GamePlays();
    private IGameActions game_actions = new GameActions(this);
    private IGameState game_state = new GameState();
    private IGameUsersScore game_user_score = new GameUsersScore();

    Timer tetrisTimer;


    private int board[][] = new int[this.BOARD_HEIGHT][this.BOARD_WIDTH];

    public Tetris(int w, int h) {
        this.WIDHT = w;
        this.HEIGHT = h;
        this.tetrisTimer = new Timer(0, this);
    }

    public void paint(Graphics g) {
        super.paint(g);

        int board_widht = WIDHT / BOARD_WIDTH;
        int board_height = HEIGHT / BOARD_HEIGHT;

        for (int h = 0; h < BOARD_HEIGHT; h += 1) {
            for (int w = 0; w < BOARD_WIDTH; w += 1) {
                g.setColor(Colors.getColor(board[h][w]));
                g.fillRect(w * board_widht, h * board_height, (w + 1) * board_widht, (h + 1) * board_height);
            }
        }

        g.setColor(BLACK);

        for (int w = 0; w <= WIDHT; w += board_widht) {
            g.drawLine(w, 0, w, HEIGHT);
        }
        for (int h = 0; h <= HEIGHT; h += board_height) {
            g.drawLine(0, h, WIDHT, h);
        }
    }
    public void init() {
        for (int i = 0; i < this.getBOARD_HEIGHT(); i++) {
            for (int j = 0; j < this.getBOARD_WIDTH(); j++) {
                board[i][j] = 0;
            }
        }
    }

    public void start() {
        game_actions.setPlay(game_plays);
        game_state.setState_GameStart();
        for (int i = 0; i < this.getBOARD_HEIGHT(); i++) {
            for (int j = 0; j < this.getBOARD_WIDTH(); j++) {
                board[i][j] = 3;
                board[i][j] = 2;
            }
        }
    }

    public void stop() {
        game_state.setState_GamePause();
    }

    public void over() {
        game_state.setState_GameOver();
        for (int i = 0; i < this.getBOARD_HEIGHT(); i++) {
            for (int j = 0; j < this.getBOARD_WIDTH(); j++) {

            }
        }
    }

    public void restart() {
        game_plays.init();
        game_actions.setPlay(game_plays);
        game_state.setState_GameStart();
        for (int h = 0; h < this.getBOARD_HEIGHT(); h++) {
            for (int w = 0; w < this.getBOARD_WIDTH(); w++) {
                board[h][w] = 0;
            }
        }
        this.tetrisTimer = new Timer(0, this);
    }

    public void resume() {
        tetrisTimer.start();
    }

    public int getBOARD_WIDTH() {
        return BOARD_WIDTH;
    }

    public int getBOARD_HEIGHT() {
        return BOARD_HEIGHT;
    }

    public Timer getTetrisTimer() {
        return tetrisTimer;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gameSpeed = 1000;
//        repaint();
        tetrisTimer.setDelay(gameSpeed);
    }

}

