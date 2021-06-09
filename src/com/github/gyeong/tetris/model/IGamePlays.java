package com.github.gyeong.tetris.model;

public interface IGamePlays {

    public void on_PressKey(int k);

    public void init();

    public void move_Left();

    public void move_Right();

    public void rotate();

    public void move_Down();

    public void move_Bottom();

    public void move_Stop();

    public boolean is_Acceptable(Tetromino tetromino);

    public void play();

    public Tetromino get_Now();

    public Tetromino get_Next();

    public void set_Now();

    public void set_Next();

    public void nextSet();

    public void board_add();

}
