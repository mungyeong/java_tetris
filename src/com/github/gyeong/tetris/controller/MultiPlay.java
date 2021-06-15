package com.github.gyeong.tetris.controller;

public class MultiPlay{
    private int state;


    public MultiPlay(Tetris tetris) {
        this.state = tetris.getState().getState();

    }

    public void update() {

    }

}
