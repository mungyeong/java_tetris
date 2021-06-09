package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.util.FileSteam;
import com.github.gyeong.tetris.util.PlayTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class GameState implements IGameState {

    public IGameScore gameScore;

    private PlayTime play_Timer = new PlayTime();

    private int state; // 0: 새 게임, 게임 오버  1: 게임 플레이  2:게임 중지

    public int getState() {
        return state;
    }

    public void init(Tetris tetris) {
        this.gameScore = tetris.getScore();
        this.state = 0;
        play_Timer.init();
    }

    public void setStart() {
        this.state = 1;
        play_Timer.start();
    }

    public void setResume() {
        this.state = 1;
        play_Timer.resume();
    }

    public void setPause() {
        this.state = 2;
        play_Timer.pause();
    }

    public void setOver() {
        this.state = 0;
        play_Timer.stop();

    }

    public long getPlaytime() {
        return play_Timer.getTime();
    }


    public void info_Save(String name, String score) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Calendar calendar = Calendar.getInstance();

        String play_day = sdf.format(calendar.getTime());

        FileSteam stream = FileSteam.getInstance();
        Map<String, Map> List = stream.readFile();
        Map<String, Map<String, String>> Rank = List.get("member");

        Map<String, String> info = new HashMap<>();
        info.put("player_name", name);
        info.put("play_time", (String.valueOf(play_Timer.getTime())));
        info.put("play_day", play_day);
        info.put("score",score);
//        info.put("score",gameScore.getStringScore());
        Rank.put(String.valueOf(Rank.keySet().size()+1), info);
        List.put("member", Rank);
        stream.writeFile(List);

    }

    public static void main(String[] args) {
       new GameState().info_Save("정문경","1000");
    }
}
