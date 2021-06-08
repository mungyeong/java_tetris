package com.github.gyeong.tetris.model;


import com.github.gyeong.tetris.util.PlayTime;

public class GameState implements IGameState {

    private PlayTime timer = new PlayTime();

    private int state; // 0: 새 게임, 게임 오버  1: 게임 플레이  2:게임 중지

    public int getState() {
        return state;
    }

    public void init() {
        this.state = 0;
        timer.init();
    }

    public void setStart() {
        this.state = 1;
        timer.start();
    }

    public void setResume() {
        this.state = 1;
        timer.resume();
    }

    public void setPause() {
        this.state = 2;
        timer.pause();
    }

    public void setOver() {
        this.state = 0;
        timer.stop();
    }

    public long getPlaytime() {
        return timer.getTime();
    }

}
