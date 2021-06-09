package com.github.gyeong.tetris.model;

public class ScoreInfo implements IScoreInfo {
    private String player_name;

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlay_time() {
        return play_time;
    }

    public void setPlay_time(String play_time) {
        this.play_time = play_time;
    }

    public String getPlay_day() {
        return play_day;
    }

    public void setPlay_day(String play_day) {
        this.play_day = play_day;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    private String play_time;
    private String play_day;
    private String score;
}
