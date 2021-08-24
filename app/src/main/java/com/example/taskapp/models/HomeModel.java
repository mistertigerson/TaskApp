package com.example.taskapp.models;

import java.io.Serializable;

public class HomeModel implements Serializable {
    private String name;
    private String time;

    public HomeModel(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
