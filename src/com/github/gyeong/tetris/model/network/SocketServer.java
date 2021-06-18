package com.github.gyeong.tetris.model.network;

import com.github.gyeong.tetris.controller.Tetris;
import com.google.gson.*;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class SocketServer extends Thread {

    private final int type = 1;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private int port = (int) (Math.random() * 10) + 1900;
    private String ip = null;
    private SocketAddress listenAddress = null;
    private NetWorkLog netWorkLog = NetWorkLog.getInstance();
    private Tetris tetris;
    private Gson gson = new GsonBuilder().create();
    volatile Send send = null;
    Read read = null;

    public SocketServer(Tetris tetris) throws IOException {
        setIp();
        this.serverSocket = new ServerSocket();
        listenAddress = new InetSocketAddress(ip, port);
        this.serverSocket.bind(listenAddress);
        this.tetris = tetris;
        System.out.println(getInfo());
    }

    @Override
    public void run() {
        try {
            netWorkLog.write("서버 접속허용\n", type);
            serverSocket.setSoTimeout(60000);
            socket = serverSocket.accept();
            socket.setTcpNoDelay(true);
            netWorkLog.write("서버 접속완료\n", type);
            send = new Send(socket, type);
            read = new Read(socket, type,tetris);
            read.start();
        } catch (IOException e) {
            netWorkLog.write(e.getMessage(), type);
        }
    }

    public int getType() {
        return type;
    }

    private boolean ipCheck(String ip) {
        return (!"127.0.0.1".matches(ip)) &&
                Pattern.matches("((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])([.](?!$)|$)){4}", ip);
    }

    private void setIp() throws SocketException {
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

    public Send getSend() {
        return send;
    }

    public String[] getInfo() {
        return new String[]{ip, String.valueOf(port)};
    }
}
