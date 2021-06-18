package com.github.gyeong.tetris.model.network;

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

    public Send(Socket socket,int type) {
        this.socket = socket;
        this.type = type;
    }

    public void attackSend() {
        try {
            send = new BufferedOutputStream(socket.getOutputStream());
                if (socket.isConnected()) {
                    String contents = "attack";
                    send.write(contents.getBytes(StandardCharsets.UTF_8));
                    send.flush();
                    netWorkLog.write("쓰기\n",type);
                }
        } catch (IOException e) {
            try {
                send.close();
                socket.close();
            } catch (IOException ioException) {
                netWorkLog.write(e.getMessage(),type);
                netWorkLog.write(ioException.getMessage(),type);
                netWorkLog.write("Send 입력스트림 닫힘\n",type);
            }
        }
    }
}

