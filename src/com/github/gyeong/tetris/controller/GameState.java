package com.github.gyeong.tetris.controller;

public class GameState implements IGameState {

    private static int state = 0; // 0:메뉴 1:게임 시작 2:게임 중지 3:게임 오버

    public int getState() {
        return state;
    }

    public void init() {
        this.state = 0;
    }

    public void setState_Menu() {
        this.state = 0;
    }

    public void setState_GameStart() {
        this.state = 1;
    }

    public void setState_GamePause() {
        this.state = 2;
    }

    public void setState_GameOver() {
        this.state = 3;
    }
}
