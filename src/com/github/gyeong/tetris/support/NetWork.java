package com.github.gyeong.tetris.support;

import java.net.*;

public class NetWork {
    private Socket Server ;
    private Socket Client ;
    private int port = (int)(Math.random() * 65535) + 49152 ;


    public void set_Server(){
        port = (int)(Math.random() * 65535) + 49152 ;
        try{
            String local = InetAddress.getLocalHost().getHostAddress();

            System.out.println(local);
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            Server = null;
            Client = null;
        }
    }

    public static void main(String[] args) {
        NetWork test = new NetWork();
        test.set_Server();
    }
}
