package com.meass.calculator_apps;

public class OnlineserModel {
    String title,short_des,line,time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_des() {
        return short_des;
    }

    public void setShort_des(String short_des) {
        this.short_des = short_des;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public OnlineserModel(String title, String short_des, String line, String time) {
        this.title = title;
        this.short_des = short_des;
        this.line = line;
        this.time = time;
    }

    public OnlineserModel() {
    }
}
