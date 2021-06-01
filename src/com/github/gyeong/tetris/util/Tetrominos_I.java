package com.github.gyeong.tetris.util;

public class Tetrominos_I extends Tetrominos {

    public Tetrominos_I() {
        this.block = new int[][][]{
                {
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                },
                {
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                }
        };
        numOfBlockType = 2;
        x = 5;
        y = 0;
        r = 0;
        widht = 4;
        height = 4;
        type = 1;
    }

    public int[][] getBlock() {
        return block[r];
    }
}