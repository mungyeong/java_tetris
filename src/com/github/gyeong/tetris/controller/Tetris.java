package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.controller.support.Colors;
import com.github.gyeong.tetris.controller.support.FileSteam;
import com.github.gyeong.tetris.model.*;
import com.github.gyeong.tetris.model.network.Read;
import com.github.gyeong.tetris.model.network.Send;
import com.github.gyeong.tetris.view.Board;
import com.github.gyeong.tetris.view.Main;
import com.github.gyeong.tetris.view.ScoreBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.Color.BLACK;

public abstract class Tetris extends JPanel implements ActionListener{

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private int widht, height, gameSpeed;

    private IGamePlays plays = new GamePlays(this);

    private IGameState state = new GameState();

    private IGameActions actions;

    private IGameScore score = new GameScore();

    private IPlayTime playtime = new PlayTime();

    private Main main = Main.getInstance();

    private Board board;

    private Tetromino now;

    private Tetromino next;

    Timer timer;

    private volatile int block[][] = new int[BOARD_HEIGHT][BOARD_WIDTH];

    public Tetris(Board board, int widht, int height) {
        this.board = board;
        this.widht = widht;
        this.height = height;
        this.actions = new GameActions(this,board);
        setFocusable(true);
        setBounds(0, 0, widht, height);
        addKeyListener(new TetrisKeyAdapter());
    }

    public void init() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                block[i][j] = 0;
            }
        }
        gameSpeed = 700;
        actions.init();
        playtime.init();
        state.init();
        score.init();
        timer = new Timer(gameSpeed, this);
        plays.init();
        now = null;
        next = null;
        repaint();
    }

    public void start() {
        plays.play();
        playtime.start();
        state.setStart();
        now = plays.get_Now();
        next = plays.get_Next();
        timer.start();
        update();
    }

    public void stop() {
        timer.stop();
        state.setOver();
        board.init();
    }

    public void over() {
        playtime.stop();
        timer.stop();
        save_Request();
        state.setOver();
        board.setType();
        board.init();
    }

    public void resume() {
        playtime.resume();
        timer.start();
        state.setResume();
    }

    public void pause() {
        playtime.pause();
        timer.stop();
        state.setPause();
    }

    public void update() {
        board.update();
        repaint();
        gameSpeed = 700 - (score.getIntScore() / 10);
        timer.setDelay(gameSpeed);
    }

    public void ScoreSave(String name) {
        IScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.setPlayer_name(name);
        scoreInfo.setScore(score.getStringScore());
        long ms = this.playtime.getTime();
        scoreInfo.setPlay_time(String.valueOf(ms));
        FileSteam stream = FileSteam.getInstance();
        stream.saveScore(scoreInfo);
        ScoreBoard.getInstance().update();
    }

    public void onBorder(Graphics g, int board_widht, int board_height) {
        for (int w = 0; w <= widht; w += board_widht) {         //게임보드 선 출력
            g.drawLine(w, 0, w, height);
        }
        for (int h = 0; h <= height; h += board_height) {
            g.drawLine(0, h, widht, h);
        }
    }

    public void onNow(Graphics g, int board_widht, int board_height) {
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

    public void onBlock(Graphics g, int board_widht, int board_height) {
        for (int h = 0; h < BOARD_HEIGHT; h++) {
            for (int w = 0; w < BOARD_WIDTH; w++) {
                Color temp = Colors.getColor(block[h][w]);
                g.setColor(temp);
                g.fillRect(w * board_widht, h * board_height, (w + 1) * board_widht, (h + 1) * board_height);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int board_widht = widht / BOARD_WIDTH;
        int board_height = height / BOARD_HEIGHT;

        onBlock(g, board_widht, board_height);

        if (now != null && this.state.getState() == 1) {
            onNow(g, board_widht, board_height);
        }
        g.setColor(BLACK);
        onBorder(g, board_widht, board_height);
    }



    class TetrisKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            plays.PressKey(keycode);
        }
    }

    public int[][] getBlock() {
        return block;
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

    public Tetromino getNext() {
        return next;
    }

    public void setNext(Tetromino next) {
        this.next = next;
    }

    public void setNow(Tetromino now) {
        this.now = now;
    }

    public abstract void save_Request();

    public abstract void attack();

    public abstract void setReadyState();

    public abstract void sendState();

    public abstract void setOver();

    public abstract void sendAttack();

    public abstract void setType();

    public abstract void setType(String ip, String port);

    public abstract String[] getInfo();

    public abstract void setSend(Send send);

    public abstract void setRead(Read read);

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.getState() == 1) {
            plays.move_Down();
        }
    }
}


