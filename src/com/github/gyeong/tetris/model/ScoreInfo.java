package com.github.gyeong.tetris.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScoreInfo implements IScoreInfo {
    private String player_name;
    private String play_time;
    private String play_day;
    private String score;

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setPlay_time(String play_time) {
        this.play_time = play_time;
    }

    public void setPlay_day(String play_day) {
        this.play_day = play_day;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public int getScoreint() {
        return Integer.valueOf(score);
    }

    public String getPlay_day() {
        return play_day;
    }

    public String getPlay_time() {
        return play_time;
    }

    public String getPlayer_name() {
        return player_name;
    }


    public ScoreInfo() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String play_day = sdf.format(Calendar.getInstance().getTime());
        this.player_name = null;
        this.play_time = null;
        this.play_day = play_day;
        this.score = null;
    }

    public String[] getString(int rank) {
        long play_time = Long.valueOf(getPlay_time()) / 1000;
        int[] unit = new int[]{60, 60};
        String s = new String();
        for (int i = 0; play_time > 0; i++) {
             if (s.isEmpty()) {
                long hour = play_time % unit[i];
                s += hour > 9 ? hour : ("0" + hour);
                play_time /= unit[i];
            } else {
                long hour = play_time % unit[i];
                s = (hour > 9 ? hour : ("0" + hour))+":"+s;
                play_time /= unit[i];
            }
        }
        return new String[]{String.valueOf(rank + 1), getPlayer_name(), getScore(), getPlay_day(), s};
    }
}
