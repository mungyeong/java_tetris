package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.controller.TetrominosFactory;
import com.github.gyeong.tetris.model.data.Tetromino;
import com.github.gyeong.tetris.view.Board;

public class GamePlays implements IGamePlays {

    private IGameActions actions;

    private IGameScore score;

    private Tetromino now;
    private Tetromino next;

    private int[][] board;

    private Tetris tetris;

    public GamePlays(Tetris tetris) {
        this.tetris = tetris;
    }

    @Override
    public void PressKey(int k) {
        actions.KeyEvent(k);
    }

    @Override
    public void init() {
        score = tetris.getScore();
        board = tetris.getBoard();
        actions = tetris.getAction();
        now = TetrominosFactory.create();
        next = TetrominosFactory.create();
    }

    @Override
    public void play() {
        this.init();
    }

    @Override
    public void move_Left() {
        now.move_Left();
        if (!is_Acceptable()) {
            now.move_Right();
        }
        tetris.update();
    }

    @Override
    public void move_Right() {
        now.move_Right();
        if (!is_Acceptable()) {
            now.move_Left();
        }
        tetris.update();
    }

    @Override
    public void rotate() {
        now.rotate();
        if (!is_Acceptable()) {
            now.preRotate();
        }
        tetris.update();
    }

    @Override
    public void move_Down() {
        now.move_Down();
        if (!is_Acceptable()) {
            move_Stop();
        } else {
            tetris.update();
        }
    }

    @Override
    public void move_Bottom() {
        while (is_Acceptable()) {
            now.move_Down();
        }
        move_Stop();
    }

    @Override
    public void move_Stop() {
        now.moveUp();
        if (now.getY() < 1) {
            tetris.over();
        } else {
            board_add();
            line_Delete();
            setNow();
            setNext();
            tetris.update();
            tetris.setNow(get_Now());
            tetris.setNext(get_Next());
            Board.getInstance().update();
        }
    }

    @Override
    public Tetromino get_Now() {
        return now;
    }

    @Override
    public Tetromino get_Next() {
        return next;
    }

    @Override
    public void setNow() {
        if (next == null) {
            this.setNext();
        }
        this.now = next;
    }

    @Override
    public void setNext() {
        this.next = TetrominosFactory.create();
    }

    @Override
    public void board_add() {
        int t_widht = now.getWidth(), t_height = now.getHeight();
        int t_x = now.getX(), t_y = now.getY();
        for (int h = 0; h < t_height; h++) {
            for (int w = 0; w < t_widht; w++) {
                if (now.getBlock()[h][w] > 0 && board[t_y + h][t_x + w] == 0) {
                    board[t_y + h][t_x + w] = now.getType();
                }
            }
        }
    }

    @Override
    public boolean is_Acceptable() {
        int[][] block = now.getBlock();
        int w = now.getWidth();
        int h = now.getHeight();
        int x = now.getX();
        int y = now.getY();

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

    @Override
    public void line_Delete() {
        int count = 0, score = 0;
        for (int y = board.length - 1; y >= 0; y--) {
            int x = 0, m;
            for (; x < 10; x++) {
                if (board[y][x] != 0) {
                    count++;
                }
            }
            if (count == board[y].length) {
                score++;
                for (x = 0; x < board[y].length; x++) {
                    for (m = y; m > 0; m--) {
                        board[m][x] = board[m - 1][x];
                    }
                    board[m][0] = 0;
                }
                y++;
            }
            count = 0;
        }
        if (score > 3) {
            score *= 40;
        } else {
            score *= 15;
        }
        this.score.setScore(score);
    }
}

