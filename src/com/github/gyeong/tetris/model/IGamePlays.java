package com.github.gyeong.tetris.model;

public interface IGamePlays {

    public void onPressKey(int k);

    public void init();

    public void moveLeft();

    public void moveRight();

    public void moveDown();

    public void rotate();

    public void moveBottom();

    public boolean isAcceptable(Tetromino tetromino);

    public void play();

    public void pause();

    public Tetromino getNow();

    public Tetromino getTetrominoe_next();

    public void setTetrominoe_now(Tetromino tetrominoe_now);

    public void setTetrominoe_next(Tetromino tetrominoe_next);

    public IGameState getGame_state();

    public void setGame_state(IGameState game_state);

    public void moveStop();

    public void next_tetrominoe();

    public void board_addblock();

}
