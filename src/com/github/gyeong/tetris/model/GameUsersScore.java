package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.view.Score;

public class GameUsersScore implements IGameUsersScore {

    private int UserScore = 0;

    private Tetris tetris;

    public GameUsersScore(Tetris tetris) {
        this.tetris = tetris;
        this.UserScore = 0;
    }

    public void init() {
        this.UserScore = 0;
    }

    @Override
    public int getUserScore() {
        return this.UserScore;
    }

    @Override
    public void setUserScore(int score) {
        this.UserScore += score;
        tetris.getGameBoard().getScore().update();
    }
}
