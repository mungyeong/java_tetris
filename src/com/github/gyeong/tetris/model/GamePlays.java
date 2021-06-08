package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.controller.TetrominosFactory;

public class GamePlays implements IGamePlays {

    private IGameActions game_actions;

    private IGameState game_state;
    private IGameUsersScore game_user_score;

    private Tetromino tetrominoe_now;
    private Tetromino tetrominoe_next;

    private int[][] board;

    private Tetris tetris;

    public GamePlays(Tetris tetris) {
        this.tetris = tetris;
    }

    @Override
    public void onPressKey(int k) {
        game_actions.onKeyEvent(k);
    }

    @Override
    public void init() {
        game_user_score = tetris.getGame_user_score();
        board = tetris.getBoard();
        game_state = tetris.getGame_state();
        game_actions = tetris.getGame_actions();
        tetrominoe_now = TetrominosFactory.create();
        tetrominoe_next = TetrominosFactory.create();

    }

    @Override
    public void play() {
        this.init();
        game_state.setResume();
    }

    @Override
    public void pause() {
        game_state.setPause();
    }

    @Override
    public void moveLeft() {
        tetrominoe_now.moveLeft();
        if (!isAcceptable(tetrominoe_now)) {
            tetrominoe_now.moveRight();
        }
        tetris.update();
    }

    @Override
    public void moveRight() {
        tetrominoe_now.moveRight();
        if (!isAcceptable(tetrominoe_now)) {
            tetrominoe_now.moveLeft();
        }
        tetris.update();
    }

    @Override
    public void moveDown() {
        tetrominoe_now.moveDown();
        if (!isAcceptable(tetrominoe_now)) {
            tetrominoe_now.moveUp();
            moveStop();
        }
        tetris.update();
    }

    @Override
    public void rotate() {
        tetrominoe_now.rotate();
        if (!isAcceptable(tetrominoe_now)) {
            tetrominoe_now.preRotate();
        }
        tetris.update();
    }

    @Override
    public void moveBottom() {
        while (isAcceptable(tetrominoe_now)) {
            tetrominoe_now.moveDown();
        }
        tetrominoe_now.moveUp();
        tetris.update();
    }

    @Override
    public void moveStop() {
        if (tetrominoe_now.getY() == 0) {
            tetris.stop();
        } else {
            board_addblock();
            next_tetrominoe();
            tetris.update();
        }
    }

    @Override
    public void next_tetrominoe() {
        tetrominoe_now = tetrominoe_next;
        tetrominoe_next = TetrominosFactory.create();
        tetris.tetromino_next();
    }

    @Override
    public void board_addblock() {
        int t_widht = tetrominoe_now.getWidth(), t_height = tetrominoe_now.getHeight();
        int t_x = tetrominoe_now.getX(), t_y = tetrominoe_now.getY();
        for (int h = 0; h < t_height; h++) {
            for (int w = 0; w < t_widht; w++) {
                if (tetrominoe_now.getBlock()[h][w] > 0) {
                    board[t_y + h][t_x + w] = tetrominoe_now.getType();
                }
            }
        }
        removeing_Line();
        tetris.update();
    }

    @Override
    public boolean isAcceptable(Tetromino tetromino) {
        int[][] block = tetromino.getBlock();
        int w = tetromino.getWidth();
        int h = tetromino.getHeight();
        int x = tetromino.getX();
        int y = tetromino.getY();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (block[i][j] != 0) {
                    if (x < 0 || (x + j) > (9) || (y + i) > (19) || y < 0) {
                        return false;
                    }
                    if (board[y + i][x + j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public void removeing_Line() {
        int count, removedLIne = 0;

        for (int y = board.length - 1; y >= 0; y--) {
            count = 0;
            int x, m;
            for (x = 0; x < 10; x++) {
                if (board[y][x] != 0) {
                    count++;
                }
            }
            if (count == board[y].length) {
                removedLIne++;
                for (x = 0; x < board[y].length; x++) {
                    for (m = y; m > 0; m--) {
                        board[m][x] = board[m - 1][x];
                    }
                    board[m][0] = 0;
                }
                y++;
            }
        }
        game_user_score.setUserScore(removedLIne * (removedLIne > 3 ? 40 : 15));
    }

    public Tetromino getNow() {
        return tetrominoe_now;
    }

    public Tetromino getTetrominoe_next() {
        return tetrominoe_next;
    }

    public void setTetrominoe_now(Tetromino tetrominoe_now) {
        this.tetrominoe_now = tetrominoe_now;
    }

    public void setTetrominoe_next(Tetromino tetrominoe_next) {
        this.tetrominoe_next = tetrominoe_next;
    }

    public IGameState getGame_state() {
        return game_state;
    }

    public void setGame_state(IGameState game_state) {
        this.game_state = game_state;
    }
}

