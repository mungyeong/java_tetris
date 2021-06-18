package com.github.gyeong.tetris.model.network;

import com.github.gyeong.tetris.controller.Tetris;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Read extends Thread {
    private NetWorkLog netWorkLog = NetWorkLog.getInstance();

    private BufferedInputStream read = null;
    private Socket socket = null;
    private Tetris tetris;

    private int type;

    Read(Socket socket, int type, Tetris tetris) {
        this.socket = socket;
        this.type = type;
        this.tetris = tetris;
    }

    public void run() {
        try {
            read = new BufferedInputStream(socket.getInputStream());

            if (socket.isClosed()) {
            }
            while (true) {
                if (socket.isConnected() && read.available() > 0) {
                    StringBuffer buffer = new StringBuffer();
                    Gson gson = new GsonBuilder().create();
                    String contents = null;
                    byte[] b = new byte[read.available()];
                    int i = 0;
                    while ((i = read.read(b)) != -1) {
                        buffer.append(new String(b, 0, i));
                        contents = new String(b, StandardCharsets.UTF_8);
                        netWorkLog.write("읽기\n", type);
                        if (contents.equals("attack")) {
                            tetris.attack();
                        }
                        buffer.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            try {
                read.close();
                socket.close();
            } catch (IOException ioException) {
                netWorkLog.write(e.getMessage(), type);
                netWorkLog.write(ioException.getMessage(), type);
                netWorkLog.write("Read 입력스트림 닫힘\n", type);
            }

        }
    }

    public void isCheck(String ip) {

    }
}


