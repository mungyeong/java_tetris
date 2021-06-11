package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.controller.Tetris;

import java.awt.event.KeyEvent;

public class GameActions implements IGameActions {

    public GameActions(Tetris tetris) {
        this.tetris = tetris;
    }
    private IGamePlays play = null;

    private IGameState state = null;

    private Tetris tetris;

    public void on_KeyEvent(int keycode) {
        if (this.play != null && this.state.getState() == 1) {
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    play.move_Left();
                    break;
                case KeyEvent.VK_RIGHT:
                    play.move_Right();
                    break;
                case KeyEvent.VK_DOWN:
                    play.move_Down();
                    break;
                case KeyEvent.VK_UP:
                    play.rotate();
                    break;
                case KeyEvent.VK_SPACE:
                    play.move_Bottom();
                    break;
                case 'h':
                case 'H':
                case 'p':
                case 'P':
                    tetris.pause();
                    break;
            }
        } else if (this.state.getState() == 0) {
            switch (keycode) {
                case 0:
                    break;
            }
        } else if (this.state.getState() == 2) {
            switch (keycode) {
                case KeyEvent.VK_SPACE:
            }
        }
    }

    @Override
    public void init() {
        this.setPlay(tetris.getPlays());
        this.setState(tetris.getState());
    }

    @Override
    public void setPlay(IGamePlays play) {
        this.play = play;
    }

    @Override
    public void setState(IGameState state) {
        this.state = state;
    }
}
