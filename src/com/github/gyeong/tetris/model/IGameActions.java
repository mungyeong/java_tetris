package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.controller.Tetris;

public interface IGameActions {

    public void on_KeyEvent(int k);

    public void setPlay(IGamePlays play);

    public void setState(IGameState state);

    public void init();

}
