package com.github.gyeong.tetris.model;

public class PlayTime implements IPlayTime {

    private long startTime;
    private long endTime;
    private long time;

    public void init() {
        startTime = 0;
        endTime = 0;
        time = 0;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void pause() {
        endTime = System.currentTimeMillis();
        time += (endTime - startTime);
    }

    public void resume() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        endTime = System.currentTimeMillis();
        time += (endTime - startTime);
    }

    public long getTime() {
        return time;
    }

}
