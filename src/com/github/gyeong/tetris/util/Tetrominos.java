package com.github.gyeong.tetris.util;

public abstract class Tetrominos {

    public Tetrominos() {

    }

    protected int widht;
    protected int height;
    protected int r;
    protected int x;
    protected int y;
    protected int type;
    protected int numOfBlockType;
    protected int block[][][];


    public void rotate() {
        r = (r+1) % numOfBlockType;
    }

    public void preRotate() {
        r = (r-1+numOfBlockType) % numOfBlockType;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }


    public void moveDown() {
        y++;
    }

    public void moveUp() {
        y--;
    }

    public int[][] getBlock() {
        return null;
    }

    public int getWidth() {
        return widht;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() { return type; }

    public void clone(Tetrominos t) {
        this.x = t.x;
        this.y = t.y;
        this.r = t.r;
    }


}
