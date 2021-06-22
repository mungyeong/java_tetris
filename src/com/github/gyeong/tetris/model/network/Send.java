package com.github.gyeong.tetris.model.network;

import com.github.gyeong.tetris.model.IScoreInfo;
import com.github.gyeong.tetris.view.Board;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Send {
    BufferedOutputStream send = null;
    Socket socket = null;
    private NetWorkLog netWorkLog = NetWorkLog.getInstance();
    private Gson gson = new GsonBuilder().create();
    private int type;
    private String contents;
    private Board board = Board.getInstance();


    public Send(Socket socket, int type) {
        this.socket = socket;
        this.type = type;
    }

    public void setAttack() {
        contents = "attack";
        Send();
    }

    public void setReady() {
        contents = "ready";
        Send();
    }

    public void setStart() {
        contents = "start";
        Send();
    }

    public void setOver(IScoreInfo scoreInfo) {
        contents = "over\n";
        Send();
        setInfo(scoreInfo);
    }

    public void setInfo(IScoreInfo scoreInfo) {
        Gson gson = new GsonBuilder().create();
        contents = gson.toJson(scoreInfo);
        Send();
    }

    public synchronized void Send() {
        try {
            send = new BufferedOutputStream(socket.getOutputStream());
            if (socket.isConnected()) {
                send.write(contents.getBytes(StandardCharsets.UTF_8));
                send.flush();
                netWorkLog.write(contents + "\t쓰기\n", type);
            }
            contents = null;
        } catch (IOException e) {
            try {
                netWorkLog.write(e.getMessage()+"\n", type);
                send.close();
                socket.close();
                netWorkLog.write("Send 입력스트림 닫힘\n", type);
            } catch (IOException ioException) {
                netWorkLog.write(ioException.getMessage()+"\n", type);
            }
        }
    }
}

