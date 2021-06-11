package com.github.gyeong.tetris.model;

public interface IGameActions {

    public void on_KeyEvent(int k);

    public void setPlay(IGamePlays play);

    public void setState(IGameState state);

    public void init();

}
