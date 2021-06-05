package com.github.gyeong.tetris.model;

public class GameState implements IGameState {

    IGamePlays game_plays;

    private static int state = 0; // 0:게임 시작 1:게임 중지 2:게임 오버

    public int getState() {
        return state;
    }

    public void init() {
        this.state = 0;
    }

    public void setState_GameStart() {
        this.state = 0;
    }

    public void setState_GamePause() {
        this.state = 1;
    }

    public void setState_GameOver() {
        this.state = 2;
    }

    public void setGame_plays(IGamePlays game_plays) {
        this.game_plays = game_plays;
    }
}
