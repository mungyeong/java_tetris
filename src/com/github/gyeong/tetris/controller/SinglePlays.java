package com.github.gyeong.tetris.controller;

import com.github.gyeong.tetris.model.*;
import com.github.gyeong.tetris.model.network.Read;
import com.github.gyeong.tetris.model.network.Send;
import com.github.gyeong.tetris.view.Board;
import com.github.gyeong.tetris.view.Main;

import javax.swing.*;

public class SinglePlays extends Tetris {


    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;

    private IGamePlays plays = new GamePlays(this);

    private Main main = Main.getInstance();

    private IGameActions actions;

    private IGameScore score = new GameScore();

    private IPlayTime playtime = new PlayTime();

    private Board board;

    private Tetromino now;

    private Tetromino next;

    public SinglePlays(Board board, int widht, int height) {
        super(board, widht, height);
        this.board = board;
        this.actions = new GameActions(this, board);
    }

    @Override
    public void save_Request() {
        if (JOptionPane.showConfirmDialog(main, "점수를 저장하시겠습니까?", "저장",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {
            String name = JOptionPane.showInputDialog(main, "저장할 이름을 입력해주세요.", "이름 입력창",
                    JOptionPane.INFORMATION_MESSAGE);
            if (name != null && !name.equals("")) {
                ScoreSave(name);
            }
        }
        board.init();
    }

    @Override
    public void attack() {

    }

    @Override
    public void setReadyState() {

    }

    @Override
    public void sendState() {

    }

    @Override
    public void sendAttack() {

    }

    @Override
    public void setType() {

    }

    @Override
    public void setType(String ip, String port) {

    }

    @Override
    public String[] getInfo() {
        return null;
    }

    @Override
    public void setSend(Send send) {

    }

    @Override
    public void setOver() {

    }

    @Override
    public void setRead(Read read) {

    }
}

