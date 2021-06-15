package com.github.gyeong.tetris.model;

public interface IGamePlays {

    public void PressKey(int k);

    public void init();

    public void move_Left();

    public void move_Right();

    public void rotate();

    public void move_Down();

    public void move_Bottom();

    public void move_Stop();

    public boolean is_Acceptable();

    public Tetromino get_Now();

    public Tetromino get_Next();

    public void setNow();

    public void setNext();

    public void board_add();

    public void line_Delete();

    public void play();

}
