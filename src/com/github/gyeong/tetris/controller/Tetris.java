package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.model.*;
import com.github.gyeong.tetris.util.Colors;
import com.github.gyeong.tetris.view.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.Color.BLACK;

public class Tetris extends JPanel implements ActionListener {

    private GameBoard gameBoard;

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private int WIDHT = 0, HEIGHT = 0, gameSpeed = 0;

    private IGamePlays game_plays = new GamePlays(this);

    private IGameActions game_actions = new GameActions();
    private IGameState game_state = new GameState();

    private IGameUsersScore game_user_score = new GameUsersScore(this);

    private Tetromino tetromino_now;

    Timer timer;

    private int board[][] = new int[BOARD_HEIGHT][BOARD_WIDTH];
    public Tetris(int w, int h, GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.WIDHT = w;
        this.HEIGHT = h;
        setFocusable(true);
        setBounds(0, 0, WIDHT, HEIGHT);
        init();
        addKeyListener(new TetrisKeyAdapter());
    }

    public void init() {
        for (int i = 0; i < getBOARD_HEIGHT(); i++) {
            for (int j = 0; j < getBOARD_WIDTH(); j++) {
                board[i][j] = 0;
            }
        }
        this.timer = new Timer(0, this);
        this.game_plays.init();
        this.game_actions.init(game_plays, game_state);
        this.game_state.init();
        this.game_user_score.init();
        this.timer = new Timer(0, this);
        this.tetromino_now = null;
        repaint();
    }

    public void start() {
        this.init();
        this.timer.start();
        this.game_plays.play();
        this.game_actions.setPlay(game_plays);
        this.game_state.setStart();
        this.tetromino_now = game_plays.getNow();
        this.gameBoard.getScore().init();
        update();
    }

    public void tetromino_next() {
        this.tetromino_now = game_plays.getNow();
    }

    public void pause() {
        this.timer.stop();
        this.game_state.setPause();
    }

    public void stop() {
        this.timer.stop();
        this.init();
        this.game_state.setOver();
        this.gameBoard.getBtn_game().setText("시작");
    }

    public void resume() {
        this.timer.start();
        this.game_state.setResume();
    }

    public int getBOARD_WIDTH() {
        return BOARD_WIDTH;
    }

    public int getBOARD_HEIGHT() {
        return BOARD_HEIGHT;
    }

    public int[][] getBoard() {
        return board;
    }

    public IGameState getGame_state() {
        return game_state;
    }

    public IGameUsersScore getGame_user_score() {
        return game_user_score;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void paint(Graphics g) {
        super.paint(g);

        int board_widht = WIDHT / BOARD_WIDTH;
        int board_height = HEIGHT / BOARD_HEIGHT;

        for (int h = 0; h < BOARD_HEIGHT; h++) {
            for (int w = 0; w < BOARD_WIDTH; w++) {
                g.setColor(Colors.getColor(board[h][w]));
                g.fillRect(w * board_widht, h * board_height, (w + 1) * board_widht, (h + 1) * board_height);
            }
        }
        if (tetromino_now != null && this.game_state.getState() == 1) {
            int t_widht = tetromino_now.getWidth(), t_height = tetromino_now.getHeight();
            int t_x = tetromino_now.getX(), t_y = tetromino_now.getY();
            for (int h = 0; h < t_height; h++) {
                for (int w = 0; w < t_widht; w++) {
                    if (tetromino_now.getBlock()[h][w] > 0) {
                        g.setColor(Colors.getColor(tetromino_now.getType()));
                        g.fillRect((t_x + w) * board_widht, (t_y + h) * board_height, board_widht, board_height);
                    }
                }
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


    @Override
    public void actionPerformed(ActionEvent e) {
        this.gameSpeed = 700 -(game_user_score.getUserScore()/10);
        this.repaint();
        this.timer.setDelay(gameSpeed);
        if (this.game_state.getState() == 1) {
            game_plays.moveDown();
        }
        System.out.println(this.timer.getDelay());
    }

    public void update() {
        this.repaint();
    }

    public IGameActions getGame_actions() {
        return game_actions;
    }

    class TetrisKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            game_plays.onPressKey(keycode);
        }
    }
}

