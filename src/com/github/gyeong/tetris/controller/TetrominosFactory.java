package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.model.data.*;

public class TetrominosFactory {

    public static Tetromino create() {
       return create ((int) (Math.random() * 7)+1);
    }

    public static Tetromino create(int type) {
        switch(type) {
                case 1:
                    return new Tetromino_I();
                case 2:
                    return new Tetromino_J();
                case 3:
                    return new Tetromino_L();
                case 4:
                    return new Tetromino_O();
                case 5:
                    return new Tetromino_S();
                case 6:
                    return new Tetromino_T();
                case 7:
                    return new Tetromino_Z();
                default:
                    return create();
        }
    }

    public static Tetromino clone(Tetromino t) {
        Tetromino block = create(t.getType());
        block.clone(t);
        return block;
    }
}