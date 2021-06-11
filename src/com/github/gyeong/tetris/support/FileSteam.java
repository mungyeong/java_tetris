package com.github.gyeong.tetris.support;

import com.github.gyeong.tetris.model.ScoreInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    public static FileSteam getInstance() {
        if (fileSteam == null) {
            fileSteam = new FileSteam();
        }
        return fileSteam;
    }

    public Map<Integer, ScoreInfo> getList() {
        return List;
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

    public void writeFile() {
        try {
            fileOutputStream = new FileOutputStream(fileName);
            write = new BufferedOutputStream(fileOutputStream);
            String contents = gson.toJson(List);
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
            if (read != null) {
                try {
                    read.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<Integer, ScoreInfo> readFile() {
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
                contents = new String(b, StandardCharsets.UTF_8);
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


    public void saveScore(ScoreInfo info) {
        List.put(List.size(), info);
        if (List.size() > 0) {
            rankSort(info);
        }
        writeFile();

    }

    public void rankSort(ScoreInfo info) {
        Map<Integer, ScoreInfo> List = this.getList();
        List<Map.Entry<Integer, ScoreInfo>> sort = new LinkedList<>(List.entrySet());
        Collections.sort(sort, (o1, o2) -> o2.getValue().getScore().compareTo((o1.getValue().getScore())));
        LinkedHashMap<Integer, ScoreInfo> result = new LinkedHashMap<>();
        for (int i = 0; i < sort.size()&&i<10; i++) {
            Map.Entry<Integer, ScoreInfo> entry = sort.get(i);
            entry.getValue().setRank(String.valueOf(i));
            result.put(entry.getKey(), entry.getValue());
        }
        this.List = result;
    }

    public void remove(int i) {
        List.remove(i);
        writeFile();
        return;
    }
}
