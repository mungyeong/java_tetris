package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.model.*;
import com.github.gyeong.tetris.model.data.ScoreInfo;
import com.github.gyeong.tetris.model.data.Tetromino;
import com.github.gyeong.tetris.view.support.Colors;
import com.github.gyeong.tetris.view.support.FileSteam;
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

public class Tetris extends JPanel implements ActionListener {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private int widht, height, gameSpeed;

    private int multiPlay = 0; // 0:싱글 1:서버 2:클라이언트

    private IGamePlays plays = new GamePlays(this);

    private IGameState state = new GameState();

    private IGameActions actions = new GameActions(this);

    private IGameScore score = new GameScore();

    private IPlayTime playtime = new PlayTime();

    private Main main = Main.getInstance();

    private Tetromino now;

    private Tetromino next;

    Timer timer;

    private int board[][] = new int[BOARD_HEIGHT][BOARD_WIDTH];

    public Tetris(int widht, int height) {
        this.widht = widht;
        this.height = height;
        setFocusable(true);
        setBounds(0, 0, widht, height);
        addKeyListener(new TetrisKeyAdapter());
    }

    public void init() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j] = 0;
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
        Board.getInstance().update();
        timer.start();
        repaint();
    }

    public void stop() {
        timer.stop();
        state.setOver();
        Board.getInstance().init();
        init();
    }

    public void over() {
        playtime.stop();
        timer.stop();
        state.setOver();
        Save_request();
        init();
        Board.getInstance().init();
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
        repaint();
        gameSpeed = 700 - (score.getIntScore() / 10);
        timer.setDelay(gameSpeed);
    }

    public void ScoreSave(String name) {
        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.setPlayer_name(name);
        scoreInfo.setScore(score.getStringScore());

        long ms = this.playtime.getTime();
        scoreInfo.setPlay_time(String.valueOf(ms));
        FileSteam stream = FileSteam.getInstance();
        stream.saveScore(scoreInfo);
        ScoreBoard.getInstance().update();
    }

    public void Save_request() {
        if (JOptionPane.showConfirmDialog(main, "점수를 저장하시겠습니까?", "저장", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {
            String name = JOptionPane.showInputDialog(main, "저장할 이름을 입력해주세요.", "이름 입력창", JOptionPane.INFORMATION_MESSAGE);
            if (name != null) {
                ScoreSave(name);
            }
        }
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
                Color temp = Colors.getColor(board[h][w]);
                g.setColor(temp);
                g.fillRect(w * board_widht, h * board_height, (w + 1) * board_widht, (h + 1) * board_height);
            }
        }
    }

    class TetrisKeyAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            plays.PressKey(keycode);
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

    public Tetromino getNext() {
        return next;
    }

    public void setNext(Tetromino next) {
        this.next = next;
    }

    public void setNow(Tetromino now) {
        this.now = now;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.getState() == 1) {
            plays.move_Down();
        }
    }
}


