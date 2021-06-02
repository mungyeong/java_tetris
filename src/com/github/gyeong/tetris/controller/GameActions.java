package com.github.gyeong.tetris.controller;

import java.awt.event.KeyEvent;

public class GameActions implements IGameActions {

    GamePlays player = null;

    public void onKeyEvent(int keycode) {
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

    public void setPlayer(GamePlays player) {
        this.player = player;
    }
}
