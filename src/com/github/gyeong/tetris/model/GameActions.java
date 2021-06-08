package com.github.gyeong.tetris.model;

import java.awt.event.KeyEvent;

public class GameActions implements IGameActions {

    private IGamePlays play = null;

    private IGameState state = null;

    public void onKeyEvent(int keycode) {
        if(this.play != null&&this.state.getState()==1){
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    play.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    play.moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    play.moveDown();
                    break;
                case KeyEvent.VK_UP:
                    play.rotate();
                    break;
                case KeyEvent.VK_SPACE:
                    play.moveBottom();
                    break;
//                case 'H':
//                case 'h':
//                    break;
                case 'p':
                case 'P':
                    break;
            }
        }
    }

    @Override
    public void init(IGamePlays play,IGameState state) {
        this.setPlay(play);
        this.setState(state);
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
