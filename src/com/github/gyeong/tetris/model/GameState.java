package com.github.gyeong.tetris.model;

public class GameState implements IGameState {

    private int state; // 0: 새 게임, 게임 오버  1: 게임 플레이  2:게임 중지

    public int getState() {
        return state;
    }

    public void init() {
        this.state = 0;
    }

    public void setStart() {
        this.state = 1;
    }

    public void setResume() {
        this.state = 1;
    }

    public void setPause() {
        this.state = 2;
    }

    public void setOver() {
        this.state = 3;
    }

}
