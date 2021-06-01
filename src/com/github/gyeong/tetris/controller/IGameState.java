package com.github.gyeong.tetris.controller;

public interface IGameState {

    public int getState();

    public void init();

    public void setState_Menu();

    public void setState_GameStart();

    public void setState_GamePause();

    public void setState_GameOver();
}
