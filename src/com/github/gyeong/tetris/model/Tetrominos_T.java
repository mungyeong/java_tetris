package com.github.gyeong.tetris.model;

public class Tetrominos_T extends Tetrominos {

    public Tetrominos_T() {
        this.block = new int[][][]{
                {
                        {1, 0, 0},
                        {1, 1, 0},
                        {1, 0, 0},
                },
                {
                        {1, 1, 1},
                        {0, 1, 0},
                        {0, 0, 0},
                },
                {
                        {0, 1, 0},
                        {1, 1, 0},
                        {0, 1, 0},
                },
                {
                        {0, 1, 0},
                        {1, 1, 1},
                        {0, 0, 0},
                }
        };
        numOfBlockType = 4;
        x = 4;
        y = 0;
        r = 0;
        widht = 3;
        height = 3;
        type = 6;
    }

    public int[][] getBlock() {
        return block[r];
    }
}