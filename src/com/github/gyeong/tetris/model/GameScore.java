package com.github.gyeong.tetris.model;


public class GameScore implements IGameScore {

    private static int Score = 0;

    public void init() {
        this.Score = 0;
    }

    @Override
    public String getStringScore() {
        return String.valueOf(this.Score);
    }

    @Override
    public int getIntScore() {
        return this.Score;
    }

    @Override
    public void setScore(int score) {
        this.Score += score;
    }
}
