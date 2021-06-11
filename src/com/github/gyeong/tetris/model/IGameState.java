package com.github.gyeong.tetris.model;

public interface IGameState {

    public int getState();

    public void init();

    public void setStart();

    public void setResume();

    public void setPause();

    public void setOver();

}
