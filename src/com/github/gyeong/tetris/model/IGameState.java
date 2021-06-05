package com.github.gyeong.tetris.model;

public interface IGameState {

    public int getState();

    public void init();

    public void setState_GameStart();

    public void setState_GamePause();

    public void setState_GameOver();
}
