package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.controller.Tetris;

import java.awt.event.KeyEvent;

public class GameActions implements IGameActions {

    Tetris t;
    IGamePlays play = null;

    public GameActions(Tetris t){
        this.t = t;
    }

    public void onKeyEvent(int keycode) {
        if(this.play != null){
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    break;
                case KeyEvent.VK_RIGHT:
                    break;
                case KeyEvent.VK_DOWN:
                    break;
                case KeyEvent.VK_UP:
                    break;
                case KeyEvent.VK_SPACE:
                    break;
                case 'H':
                case 'h':
                    break;
                case 'p':
                case 'P':
                    break;
            }
        }
    }


    @Override
    public void setPlay(IGamePlays play) {
        this.play = play;
    }
}
