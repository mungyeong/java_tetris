package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.controller.TetrominosFactory;

public class GamePlays implements IGamePlays {

    private IGamePlays game_plays;
    private IGameActions game_actions;
    private IGameState game_state;

    private Tetromino tetrominoe_now;
    private Tetromino tetrominoe_next;
    


    @Override
    public void onPressKey(int k) {
        game_actions.onKeyEvent(k);
    }

    @Override
    public void init() {
        tetrominoe_now = TetrominosFactory.create();
        tetrominoe_next = TetrominosFactory.create();
    }

    @Override
    public void play() {
        this.init();
        game_state.setState_GameStart();
    }
    @Override
    public void pause() {
        game_state.setState_GamePause();
    }

    @Override
    public int getGameState() {
        return game_state.getState();
    }

    @Override
    public void moveLeft(Tetromino tetromino, int[][] original) {
    }
    @Override
    public void moveRight(Tetromino tetromino, int[][] original) {

    }

    @Override
    public void moveDown(Tetromino tetromino, int[][] original) {
        
    }

    @Override
    public void rotate(Tetromino tetromino, int[][] original) {
        
    }

    @Override
    public void moveBottom(Tetromino tetromino, int[][] original) {
        
    }

    @Override
    public boolean isAcceptable(Tetromino tetromino, int[][] original) {
        int[][] block = tetromino.getBlock();
        int w = tetromino.getWidth();
        int h = tetromino.getHeight();
        int x = tetromino.getX();
        int y = tetromino.getY();



        return true;
    }


}
