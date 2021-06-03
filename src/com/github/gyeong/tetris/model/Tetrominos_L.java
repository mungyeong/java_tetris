package com.github.gyeong.tetris.model;

public class Tetrominos_L extends Tetrominos {

    public Tetrominos_L() {
        this.block = new int[][][]{
                {
                        {1, 0, 0},
                        {1, 0, 0},
                        {1, 1, 0},
                },
                {
                        {1, 1, 1},
                        {1, 0, 0},
                        {0, 0, 0},
                },
                {
                        {1, 1, 0},
                        {0, 1, 0},
                        {0, 1, 0},
                },
                {
                        {0, 0, 0},
                        {0, 0, 1},
                        {1, 1, 1},
                }
        };
        numOfBlockType = 4;
        x = 4;
        y = 0;
        r = 0;
        widht = 3;
        height = 3;
        type = 3;
    }

    public int[][] getBlock() {
        return block[r];
    }

}