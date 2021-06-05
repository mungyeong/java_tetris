package com.github.gyeong.tetris.model;

import java.awt.*;

public interface IGamePlays {

    public void onPressKey(int k);

    public void init();

    public void moveLeft(Tetromino tetromino, int[][] original);

    public void moveRight(Tetromino tetromino, int[][] original);

    public void moveDown(Tetromino tetromino, int[][] original);

    public void rotate(Tetromino tetromino, int[][] original);

    public void moveBottom(Tetromino tetromino, int[][] original);

    public boolean isAcceptable(Tetromino tetromino, int[][] original);

    public void play();

    public void pause();

    public int getGameState();
}
