package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.model.*;
import com.github.gyeong.tetris.util.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.Color.BLACK;

public class Tetris extends JPanel implements ActionListener {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private int widht, height, gameSpeed = 0;

    private IGamePlays plays = new GamePlays(this);

    private IGameState state = new GameState();

    private IGameActions actions = new GameActions(this);

    private IGameScore score = new GameScore();

    private Tetromino now;
    Timer timer;

    private int board[][] = new int[BOARD_HEIGHT][BOARD_WIDTH];

    public Tetris(int widht, int height) {
        this.widht = widht;
        this.height = height;
        setFocusable(true);
        setBounds(0, 0, widht, height);
        init();
        addKeyListener(new TetrisKeyAdapter());
    }

    public void init() {
        timer = new Timer(0, this);
        actions.init();
        plays.init();
        state.init(this);
        timer = new Timer(0, this);
        now = null;
        repaint();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void start() {
        timer.start();
        plays.play();
        state.setStart();
        nextSet();
        repaint();
    }

    public void stop() {
        timer.stop();
        state.setOver();
        init();
    }

    public void resume() {
        timer.start();
        state.setResume();
    }

    public void pause() {
        timer.stop();
        state.setPause();
    }

    public void nextSet() {
        now = plays.get_Now();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int board_widht = widht / BOARD_WIDTH;
        int board_height = height / BOARD_HEIGHT;

        for (int h = 0; h < BOARD_HEIGHT; h++) {
            for (int w = 0; w < BOARD_WIDTH; w++) {
                Color temp = Colors.getColor(board[h][w]);
                g.setColor(temp);
                g.fillRect(w * board_widht, h * board_height, (w + 1) * board_widht, (h + 1) * board_height);
            }
        }
        if (now != null && this.state.getState() == 1) {
            int t_widht = now.getWidth(), t_height = now.getHeight();
            int t_x = now.getX(), t_y = now.getY();
            for (int h = 0; h < t_height; h++) {
                for (int w = 0; w < t_widht; w++) {
                    if (now.getBlock()[h][w] > 0) {
                        Color temp = Colors.getColor(now.getType());
                        g.setColor(temp);
                        g.fillRect((t_x + w) * board_widht, (t_y + h) * board_height, board_widht, board_height);
                    }
                }
            }
        }
        g.setColor(BLACK);

        for (int w = 0; w <= widht; w += board_widht) {
            g.drawLine(w, 0, w, height);
        }
        for (int h = 0; h <= height; h += board_height) {
            g.drawLine(0, h, widht, h);
        }
    }

    public void update() {
        this.repaint();
    }

    class TetrisKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            plays.on_PressKey(keycode);
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public IGameState getState() {
        return state;
    }

    public IGameScore getScore() {
        return score;
    }

    public IGameActions getAction() {
        return actions;
    }

    public IGamePlays getPlays() {
        return plays;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameSpeed = 700 - (score.getIntScore() / 10);
        repaint();
        timer.setDelay(gameSpeed);
        if (state.getState() == 1) {
            plays.move_Down();
        }
    }
}

