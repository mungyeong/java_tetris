package com.github.gyeong.tetris.controller.support;

import com.github.gyeong.tetris.model.ScoreInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileSteam {

    private static FileSteam fileSteam;

    private InputStream fileInputStream;
    private OutputStream fileOutputStream;
    private BufferedOutputStream write;
    private BufferedInputStream read;

    private static Map<Integer, ScoreInfo> List = getInstance().readFile();

    private Gson gson = new GsonBuilder().create();

    private File folder = new File("./file");

    private File fileName = new File(folder + "/Scoreboard.json");

    private String line = System.getProperty("line.separator");


    public static FileSteam getInstance() {
        if (fileSteam == null) {
            fileSteam = new FileSteam();
        }
        return fileSteam;
    }

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

    public synchronized void writeFile() {
        try {
            fileOutputStream = new FileOutputStream(fileName);
            write = new BufferedOutputStream(fileOutputStream);
            String contents = gson.toJson(List).replace("\n", line);
            write.write(contents.getBytes(StandardCharsets.UTF_8));
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
        }
    }

    public synchronized void writeFile(String fileName, String contents) {
        try {
            File newfile = new File(this.folder + fileName);
            if (!newfile.exists()) {
                newfile.createNewFile();
            }
            fileOutputStream = new FileOutputStream(newfile, true);
            write = new BufferedOutputStream(fileOutputStream);
            write.write(contents.replace("\n", line).getBytes(StandardCharsets.UTF_8));
            write.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                write.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized Map<Integer, ScoreInfo> readFile() {
        String contents = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            read = new BufferedInputStream(fileInputStream);
            StringBuffer buffer = new StringBuffer();
            int i = 0;
            if (read.available() > 0) {
                byte[] b = new byte[read.available()];
                while ((i = read.read(b)) != -1) {
                    buffer.append(new String(b, 0, i));
                }
                contents = buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        List = new HashMap<>();
        if (contents != null) {
            List = gson.fromJson(contents, new TypeToken<LinkedHashMap<Integer, ScoreInfo>>() {
            }.getType());
        }
        return List;
    }


    public synchronized void saveScore(ScoreInfo info) {
        rankSort(info);
        writeFile();
        List = getInstance().readFile();
    }

    public synchronized void rankSort(ScoreInfo info) {
        int i = 0;
        for (; i < List.size(); i++) {
            ScoreInfo b = List.get(i);
            int score = b.getScoreint();
            if (info.getScoreint() >= score) {
                for (int j = List.size() - 1; j >= i; j--) {
                    ScoreInfo s = List.get(j);
                    List.put(j + 1, s);
                }
                break;
            }
        }
        List.put(i, info);
    }

    public synchronized void rankSort(int index) {
        int lastNumber = Collections.max(List.keySet());
        do {
            ScoreInfo info = List.get(index + 1);
            List.put(index, info);
            index ++;
        } while (index < lastNumber);
        List.remove(lastNumber);
    }

    public synchronized void remove(int i) {
        List.remove(i);
        if(List.size()>0){
            rankSort(i);
        }
        writeFile();
    }

    public synchronized Map<Integer, ScoreInfo> getList() {
        return List;
    }
}
