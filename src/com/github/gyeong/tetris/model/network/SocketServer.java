package com.github.gyeong.tetris.model.network;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.view.Board;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class SocketServer extends NetWork {

    private final int type = 1;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private int port = (int) (Math.random() * 10) + 1900;
    private String ip = null;
    private SocketAddress listenAddress = null;
    private NetWorkLog netWorkLog = NetWorkLog.getInstance();
    private Tetris tetris;
    private Gson gson = new GsonBuilder().create();
    private volatile Send send = null;
    private volatile Read read = null;

    private Board board = Board.getInstance();

    public SocketServer(Tetris tetris) throws IOException {
        setInfo();
        this.serverSocket = new ServerSocket();
        listenAddress = new InetSocketAddress(ip, port);
        this.serverSocket.bind(listenAddress);
        this.tetris = tetris;
    }

    @Override
    public void run() {
        try {
            netWorkLog.write("서버 접속허용\n", type);
            serverSocket.setSoTimeout(60000);
            socket = serverSocket.accept();
            socket.setTcpNoDelay(true);
            netWorkLog.write("서버 접속완료\n", type);
            board.updateBtn(true);
            send = new Send(socket, type);
            read = new Read(socket, type, tetris);
            read.start();
            tetris.setRead(read);
            tetris.setSend(send);
        } catch (IOException e) {
            netWorkLog.write(e.getMessage()+"\n", type);
            board.showError(e.getMessage());
            board.setType();
            board.setTetris(false);
            board.updateBtn(true);
            board.init();
        }
    }

    public int getType() {
        return type;
    }

    public static boolean ipCheck(String ip) {
        if(ip == null) {
            return false;
        }
        return (!"127.0.0.1".matches(ip)) &&
                Pattern.matches("((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])([.](?!$)|$)){4}", ip);
    }

    private void setInfo() throws SocketException {
        Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
        while (n.hasMoreElements()) {
            NetworkInterface e = n.nextElement();
            Enumeration<InetAddress> a = e.getInetAddresses();
            while (a.hasMoreElements()) {
                InetAddress addr = a.nextElement();
                String ip = addr.getHostAddress();
                if (ipCheck(ip)) {
                    this.ip = ip;
                }
            }
        }
    }
    public String[] getInfo() {
        return new String[]{ip, String.valueOf(port)};
    }
}
