package com.example.sgholidays;

public class Holiday {
    private String name;
    private String date;
    private String intro;
    public Holiday(String name, String date, String intro) {
        this.name = name;
        this.date = date;
        this.intro = intro;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getIntro() {
        return intro;
    }
}
