package com.github.gyeong.tetris.model.network;

import com.github.gyeong.tetris.controller.support.FileSteam;

public class NetWorkLog {
    private FileSteam fileSteam = FileSteam.getInstance();

    private String line = System.getProperty("line.separator");

    private static NetWorkLog netWorkLog;

    private NetWorkLog(){}

    public static NetWorkLog getInstance() {
        if (netWorkLog == null) {
            netWorkLog = new NetWorkLog();
        }
        return netWorkLog;
    }

    public void write(String contents,int type) {
        String file_name = "/networklog.txt";
        if (type == 0) {
            file_name = "/networklog_Client.log";
        }
        if (type == 1) {
            file_name = "/networklog_Server.log";
        }
        fileSteam.writeFile(file_name, contents.replace("\n", line));
    }
}
