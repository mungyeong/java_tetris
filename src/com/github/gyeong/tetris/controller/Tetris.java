package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.controller.support.Colors;
import com.github.gyeong.tetris.controller.support.FileSteam;
import com.github.gyeong.tetris.model.*;
import com.github.gyeong.tetris.model.network.*;
import com.github.gyeong.tetris.view.Board;
import com.github.gyeong.tetris.view.Main;
import com.github.gyeong.tetris.view.ScoreBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.awt.Color.BLACK;

public class Tetris extends JPanel implements ActionListener {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private int widht, height, gameSpeed;

    private boolean readyState = false; // 준비중 false 준비완료 true

    private IGamePlays plays = new GamePlays(this);

    private IGameState state = new GameState();

    private IGameActions actions = new GameActions(this);

    private IGameScore score = new GameScore();

    private IPlayTime playtime = new PlayTime();

    private Main main = Main.getInstance();

    private Board board;

    private NetWork netWork;

    private NetWorkLog netWorkLog = NetWorkLog.getInstance();

    private Tetromino now;

    private Tetromino next;

    private Send send;

    private Read read;

    Timer timer;

    private volatile int block[][] = new int[BOARD_HEIGHT][BOARD_WIDTH];

    public Tetris(Board board, int widht, int height) {
        this.board = board;
        this.widht = widht;
        this.height = height;
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
        netWork = null;
        readyState = false;
        repaint();
    }

    public void start() {
        if (read != null || send != null) {
            if (readyState) {
                plays.play();
                playtime.start();
                state.setStart();
                now = plays.get_Now();
                next = plays.get_Next();
                timer.start();
                update();
            } else {
                send.setReady();
                ready();
            }
        } else {
            plays.play();
            playtime.start();
            state.setStart();
            now = plays.get_Now();
            next = plays.get_Next();
            timer.start();
            update();
        }
    }

    public void stop() {
        timer.stop();
        state.setOver();
        board.init();
    }

    public void over() {
        playtime.stop();
        timer.stop();
        state.setOver();
        Save_request();
        board.init();
        if (read != null || send != null) {
            read.interrupt();
            read = null;
            send = null;
        }
        readyState = false;
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
        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.setPlayer_name(name);
        scoreInfo.setScore(score.getStringScore());
        long ms = this.playtime.getTime();
        scoreInfo.setPlay_time(String.valueOf(ms));
        FileSteam stream = FileSteam.getInstance();
        stream.saveScore(scoreInfo);
        ScoreBoard.getInstance().update();
    }

    public ScoreInfo ScoreSave() {
        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.setScore(score.getStringScore());
        long ms = this.playtime.getTime();
        scoreInfo.setPlay_time(String.valueOf(ms));
        return scoreInfo;
    }

    public void Save_request() {
        if (read == null && send == null) {
            if (JOptionPane.showConfirmDialog(main, "점수를 저장하시겠습니까?", "저장",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {
                String name = JOptionPane.showInputDialog(main, "저장할 이름을 입력해주세요.", "이름 입력창",
                        JOptionPane.INFORMATION_MESSAGE);
                if (name != null) {
                    ScoreSave(name);
                }
            }
        } else {
            ScoreInfo sendInfo = ScoreSave();
            sendOver(sendInfo);
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
                Color temp = Colors.getColor(block[h][w]);
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

    public void attack() {
        plays.line_Attack();
        for (int t : block[0]) {
            if (t != 0) {
                over();
            }
        }
        update();
    }

    public void ready() {
        if (!readyState) {
            readyState = true;
        } else {
            sendStart();
            start();
        }
    }

    public void sendAttack() {
        this.send.setAttack();
    }

    public void sendStart() {
        this.send.setStart();
    }

    public void sendOver(ScoreInfo sendInfo) {
        this.send.setOver(sendInfo);
    }


    public void setType() {
        try {
            this.netWork = new SocketServer(this);
            this.netWork.start();
        } catch (IOException e) {
            netWorkLog.write(e.getMessage(), 1);
        }
    }

    public void setType(String ip, String port) {
        if (ip != null && port != null) {
            this.netWork = new SocketClient(ip, Integer.valueOf(port), this);
            this.netWork.start();
        }
    }

    public boolean isMultiGame() {
        return send != null && read != null;
    }


    public String[] getInfo() {
        return netWork.getInfo();
    }

    public void setSend(Send send) {
        this.send = send;
    }

    public void setRead(Read read) {
        this.read = read;
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
}


