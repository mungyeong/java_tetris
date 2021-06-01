package com.github.gyeong.tetris.util;

public class Tetrominos_J extends Tetrominos {

    public Tetrominos_J() {
        this.block = new int[][][]{
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {1, 1, 0},
                },
                {
                        {1, 0, 0},
                        {1, 1, 1},
                        {0, 0, 0},
                },
                {
                        {1, 1, 0},
                        {1, 0, 0},
                        {1, 0, 0},
                },
                {
                        {1, 1, 1},
                        {0, 0, 1},
                        {0, 0, 0},
                }
        };
        numOfBlockType = 4;
        x = 4;
        y = 0;
        r = 0;
        widht = 3;
        height = 3;
        type = 2;
    }

    public int[][] getBlock() {
        return block[r];
    }
}