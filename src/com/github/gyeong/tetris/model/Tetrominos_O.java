package com.github.gyeong.tetris.model;

public class Tetrominos_O extends Tetrominos {

    public Tetrominos_O() {
        this.block = new int[][][]{
                {
                        {1, 1},
                        {1, 1}
                },
        };
        numOfBlockType = 1;
        x = 4;
        y = 0;
        r = 0;
        widht = 2;
        height = 2;
        type = 4;
    }

    public void rotate() {
        r = 0;
    }
    public int[][] getBlock() {
        return block[r];
    }
}