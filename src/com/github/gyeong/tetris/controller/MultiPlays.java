package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.model.*;
import com.github.gyeong.tetris.model.network.*;
import com.github.gyeong.tetris.view.Board;

import java.awt.event.ActionListener;
import java.io.IOException;

public class MultiPlays extends Tetris implements ActionListener {

    private final int BOARD_WIDTH = 10;

    private final int BOARD_HEIGHT = 20;

    private IGamePlays plays = new GamePlays(this);

    private IGameScore score = new GameScore();

    private IPlayTime playtime = new PlayTime();

    private Board board;

    private boolean readyState = false;

    private boolean overState = false;

    private NetWork netWork;

    private NetWorkLog netWorkLog = NetWorkLog.getInstance();

    private Send send;

    private Read read;

    private volatile int block[][] = new int[BOARD_HEIGHT][BOARD_WIDTH];

    public MultiPlays(Board board, int widht, int height) {
        super(board, widht, height);
        this.board = board;
    }

    public void init() {
        if (read != null) {
            read.interrupt();
        }
        plays.init();
        read = null;
        playtime.init();
        score.init();
        super.init();
    }

    public void start() {
        super.start();
        playtime.start();
        board.updateBtn(false);
    }

    public void stop() {
        super.stop();
    }

    public void over() {
        if(!overState){
            overState = true;
            send.setOver();
        }
        super.over();
        send = null;
        readyState = false;
        board.setTetris(false);
    }

    public void resume() {
        super.resume();
    }

    public void pause() {
        super.pause();
    }

    public void save_Request() {
        if(overState){
            sendInfo(ScoreSave());
        }
    }

    public void setOver() {
        overState = true;
        over();
    }

    public void sendInfo(IScoreInfo scoreInfo) {
        send.setInfo(scoreInfo);
    }

    public IScoreInfo ScoreSave() {
        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.setScore(score.getStringScore());
        long ms = this.playtime.getTime();
        scoreInfo.setPlay_time(String.valueOf(ms));
        return scoreInfo;
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

    public void setReadyState() {
        if (!readyState) {
            readyState = true;
        } else {
            start();
        }
    }

    public void sendAttack() {
        send.setAttack();
    }

    public void sendState() {
        if (readyState) {
            start();
            send.setStart();
        } else {
            readyState = true;
            send.setReady();
        }
    }

    public void setType() {
        try {
            netWork = new SocketServer(this);
            netWork.start();
        } catch (IOException e) {
            netWorkLog.write(e.getMessage(), 1);
        }
    }

    public void setType(String ip, String port) {
        if (ip != null && port != null) {
            netWork = new SocketClient(ip, Integer.valueOf(port), this);
            netWork.start();
        }
    }

    public String[] getInfo() {
        return netWork.getInfo();
    }

    public void setSend(Send send) {
        this.send = send;
    }

    public void setRead(Read read) {
        if (read == null) {
            read.interrupt();
        }
        this.read = read;
    }
}