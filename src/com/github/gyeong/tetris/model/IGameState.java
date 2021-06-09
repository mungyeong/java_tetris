package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.controller.Tetris;

public interface IGameState {

    public int getState();

    public void init(Tetris tetris);

    public void setStart();

    public void setResume();

    public void setPause();

    public void setOver();

    public long getPlaytime();

}
