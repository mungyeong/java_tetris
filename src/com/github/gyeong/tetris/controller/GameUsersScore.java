package com.github.gyeong.tetris.controller;

public class GameUsersScore implements IGameUsersScore{
    private GameState gs;
    private int UserScore = 0;

    public void init() {
    }

    @Override
    public int getUserScore() {
        return UserScore;
    }

    @Override
    public void setUserScore(int userScore) {
        UserScore = userScore;
    }
}
