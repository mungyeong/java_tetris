package com.github.gyeong.tetris.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FileSteam {

    private static FileSteam fileSteam;

    private InputStream fileInputStream;
    private OutputStream fileOutputStream;
    private BufferedOutputStream write;
    private BufferedInputStream read;

    private Gson gson = new GsonBuilder().create();

    private File folder = new File("./file");

    private File fileName = new File(folder + "/Scoreboard.json");

    private FileSteam() {
        try {
            if (!folder.exists()) {
                folder.mkdir();
            }
            if (!fileName.exists()) {
                fileName.createNewFile();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void writeFile(Map<String, Map> List) {
        try {
            fileOutputStream = new FileOutputStream(fileName, true);
            fileInputStream = new FileInputStream(fileName);
            write = new BufferedOutputStream(fileOutputStream);
            read = new BufferedInputStream(fileInputStream);

            String contents = gson.toJson(List);
            int i = read.available();
            if ( i < 1) {
                write.write(contents.getBytes(StandardCharsets.UTF_8));
            } else {
                write.write(",".getBytes(StandardCharsets.UTF_8));
                write.write(contents.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (write != null) {
                try {
                    write.flush();
                    write.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (read != null) {
                try {
                    read.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<String,Map> readFile() {
        String contents = null;
        Map<String, Map> retrun_value = new HashMap<>();
//        retrun_value.put("member",);
        try {
            fileInputStream = new FileInputStream(fileName);
            read = new BufferedInputStream(fileInputStream);
            StringBuffer buffer = new StringBuffer();
            int i =0;
            byte[] b = new byte[read.available()];
            while((i = read.read(b)) != -1){
                buffer.append(new String(b, 0, i));
            }
            contents = new String(b,StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(contents!=null) {
            return (Map<String, Map>) gson.fromJson(contents, Map.class);
        }
        return new HashMap<>();

    }

    public static FileSteam getInstance() {
        if (fileSteam == null) {
            fileSteam = new FileSteam();
        }
        return fileSteam;
    }
}
