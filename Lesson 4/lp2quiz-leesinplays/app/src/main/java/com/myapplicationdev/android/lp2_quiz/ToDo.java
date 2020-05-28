package com.myapplicationdev.android.lp2_quiz;

import java.io.Serializable;

public class ToDo implements Serializable {
    private int id;
    private String date;
    private String data;

    public ToDo(int id, String date, String data) {
        this.id = id;
        this.date = date;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Id=" + id + ", Date='" + date + '\'' +
                ", Data='" + data + '\'';
    }
}
