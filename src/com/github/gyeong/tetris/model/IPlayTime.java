package com.github.gyeong.tetris.model;

public interface IPlayTime {
    public void init();

    public void start();

    public void pause();

    public void resume();

    public void stop();

    public long getTime();
}
