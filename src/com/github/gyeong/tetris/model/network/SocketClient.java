package com.github.gyeong.tetris.model.network;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.view.Board;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.Socket;

public class SocketClient extends NetWork {

    private final int type = 0;
    private Socket client;
    private String ip;
    private int port = 0;
    private Tetris tetris = null;
    private NetWorkLog netWorkLog = NetWorkLog.getInstance();
    private Gson gson = new GsonBuilder().create();
    private volatile Send send = null;
    private Read read = null;
    private Board board = Board.getInstance();

    public SocketClient(String ip, int port, Tetris tetris) {
        try {
            this.ip = ip;
            this.port = port;
            this.tetris = tetris;
            this.client = new Socket(ip, port);
        } catch (IOException e) {
            board.showError(e.getMessage());
            netWorkLog.write(e.getMessage(), type);
        }
    }

    @Override
    public void run() {
        try {
            netWorkLog.write("서버 접속완료\n", type);
            client.setTcpNoDelay(true);
            if(client.isConnected()){
                board.updateBtn(true);
            }
            send = new Send(client, type);
            read = new Read(client, type, tetris);
            read.start();
            tetris.setSend(send);
            tetris.setRead(read);
        } catch (IOException e) {
            netWorkLog.write(e.getMessage()+"\n", type);
            board.showError(e.getMessage());
            board.init();
            board.updateBtn(true);
            board.Btn_init();
        }
    }

    public int getType() {
        return type;
    }

    public String[] getInfo() {
        return new String[]{ip, String.valueOf(port)};
    }

}
