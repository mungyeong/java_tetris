package com.github.gyeong.tetris.model.network;

public abstract class NetWork extends Thread {
    public abstract int getType();

    public abstract String[] getInfo();
}
