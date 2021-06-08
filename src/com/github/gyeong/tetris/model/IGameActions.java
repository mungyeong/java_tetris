package com.github.gyeong.tetris.model;

public interface IGameActions {
    public void onKeyEvent(int k);

    public void setPlay(IGamePlays play);

    public void setState(IGameState state);

    public void init(IGamePlays play,IGameState state);

}
