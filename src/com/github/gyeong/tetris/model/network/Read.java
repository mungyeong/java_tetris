package com.github.gyeong.tetris.model.network;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.model.IScoreInfo;
import com.github.gyeong.tetris.model.ScoreInfo;
import com.github.gyeong.tetris.view.Board;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;

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
            while (true) {
                read = new BufferedInputStream(socket.getInputStream());
                if (socket.isConnected()) {
                    Gson gson = new GsonBuilder().create();
                    String contents = null;
                    byte[] b = new byte[1024];
                    int i = 0;
                    while ((i = read.read(b)) != -1) {
                        contents = new String(b, 0, i);
                        if (contents == "") {
                            continue;
                        }
                        if (contents.startsWith("attack")) {
                            tetris.attack();
                            netWorkLog.write("공격당함\n", type);
                        }
                        if (contents.startsWith("ready")) {
                            tetris.setReadyState();
                            netWorkLog.write("준비\n", type);
                        }
                        if (contents.startsWith("start")) {
                            tetris.setReadyState();
                            netWorkLog.write("시작\n", type);
                        }
                        if (contents.startsWith("over")) {
                            if (tetris.getState().getState() != 3) {
                                tetris.setOver();
                                netWorkLog.write("오버\n", type);
                            }
                        }
                        if(contents.startsWith("Info")) {
                            Integer opponent = gson.fromJson(contents.replace("Info\n",""), ScoreInfo.class).getScoreint();
                            board.showScore(opponent);
                            netWorkLog.write("정보 읽기\n", type);
                        }
                    }
                }
            }
        } catch (IOException e) {
            try {
                read.close();
                socket.close();
                netWorkLog.write(e.getMessage() + "\n", type);
                netWorkLog.write("Read 입력스트림 닫힘\n", type);
            } catch (IOException ioException) {
                netWorkLog.write(ioException.getMessage() + "\n", type);
            }

        }
    }
}


