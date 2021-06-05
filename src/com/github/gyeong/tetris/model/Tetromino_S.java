package com.github.gyeong.tetris.model;

public class Tetromino_S extends Tetromino {

    public Tetromino_S() {
        this.block = new int[][][]{
                {
                        {0, 1, 1},
                        {1, 1, 0},
                        {0, 0, 0},
                },
                {
                        {1, 0, 0},
                        {1, 1, 0},
                        {0, 1, 0},
                },
        };
        numOfBlockType = 2;
        x = 4;
        y = 0;
        r = 0;
        widht = 3;
        height = 3;
        type = 5;
    }

    public int[][] getBlock() {
        return block[r];
    }
}