package com.github.gyeong.tetris.model.network;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.view.Board;
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
    private Board board = Board.getInstance();

    private int type;

    Read(Socket socket, int type, Tetris tetris) {
        this.socket = socket;
        this.type = type;
        this.tetris = tetris;
    }

    public synchronized void run() {
        try {
            read = new BufferedInputStream(socket.getInputStream());
            while (true) {
                if (socket.isConnected() && read.available() > 0) {
                    StringBuffer buffer = new StringBuffer();
                    Gson gson = new GsonBuilder().create();
                    String contents = null;
                    byte[] b = new byte[1024];
                    int i = 0;
                    while ((i = read.read(b)) != -1) {
                        buffer.append(new String(b, 0, i));
                        contents = new String(b, StandardCharsets.UTF_8);
                        if (contents.startsWith("attack")) {
                            tetris.attack();
                            netWorkLog.write("공격당함\n", type);
                        }
                        if (contents.startsWith("ready")) {
                            tetris.ready();
                            netWorkLog.write("준비완료\n", type);
                        }
                        if (contents.startsWith("start")) {
                            tetris.start();
                            netWorkLog.write("시작\n", type);
                        }
                        if (contents.startsWith("over")) {
                            if(tetris.getState().getState() != 3){
                                board.showScore(contents);
                                tetris.over();
                            }
                            netWorkLog.write("오버\n", type);
                        }
                        buffer = new StringBuffer();
                        b = new byte[1024];
                    }
                }
            }
        } catch (IOException e) {
            try {
                read.close();
                socket.close();
                netWorkLog.write(e.getMessage()+"\n", type);
                netWorkLog.write("Read 입력스트림 닫힘\n", type);
            } catch (IOException ioException) {
                netWorkLog.write(ioException.getMessage()+"\n", type);
            }

        }
    }
}


