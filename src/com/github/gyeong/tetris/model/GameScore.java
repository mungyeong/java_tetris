package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.view.GameBoard;

public class GameScore implements IGameScore {

    private int Score = 0;

    public GameScore() {
        init();
    }

    public void init() {
        this.Score = 0;
        GameBoard.getInstance().update_Score(Score);
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
        GameBoard.getInstance().update_Score(Score);
    }
}
