package com.github.gyeong.tetris.model;

public interface IScoreInfo {
    public String getPlayer_name();

    public void setPlayer_name(String player_name);

    public String getPlay_time();

    public void setPlay_time(String play_time);

    public String getPlay_day();

    public void setPlay_day(String play_day);

    public String getScore();

    public int getScoreint();

    public void setScore(String score);

    public void setRank(String rank);

    public String getRank();

    public String[] getString() ;
}
