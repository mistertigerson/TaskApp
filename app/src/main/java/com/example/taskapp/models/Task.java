package com.example.taskapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.taskapp.App;
import com.example.taskapp.room.TaskDao;
import com.example.taskapp.ui.home.HomeFragment;

import java.io.Serializable;

@Entity
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String time;

    public Task(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
