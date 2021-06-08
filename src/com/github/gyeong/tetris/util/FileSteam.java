package com.github.gyeong.tetris.util;

import java.io.*;

public class FileSteam {

    private static FileSteam fileSteam;

    private static InputStream fileInputStream;
    private static OutputStream fileOutputStream;

    private File fileName = new File("gameScoreboard.txt");

    private FileSteam() {
        try{
            fileName.createNewFile();
            fileOutputStream = new FileOutputStream(fileName);
            fileInputStream = new FileInputStream(fileName);
        }
        catch (Exception e) {

        }
    }

    public static void writeFile() {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//        bufferedOutputStream.write();
//        bufferedOutputStream.close();
    }

    public static FileSteam getInstance() {
        if(fileSteam== null){
            fileSteam = new FileSteam();
        }
        return fileSteam;
    }


}
