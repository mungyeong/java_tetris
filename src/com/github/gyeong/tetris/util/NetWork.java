package com.github.gyeong.tetris.util;

import java.net.*;

public class NetWork {
    private Socket Server ;
    private Socket Client ;
    private int port = (int)(Math.random() * 65535) + 49152 ;


    public void set_Server(){
        try{
            String local = InetAddress.getLocalHost().getHostAddress();

            System.out.println(local);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Server = null;
            Client = null;
            port = (int)(Math.random() * 65535) + 49152 ;
        }
    }

    public static void main(String[] args) {
        NetWork test = new NetWork();
        test.set_Server();
    }
}
