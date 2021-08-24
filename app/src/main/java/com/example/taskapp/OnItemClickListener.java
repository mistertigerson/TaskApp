package com.example.taskapp;

import android.view.View;

import com.example.taskapp.models.HomeModel;

public interface OnItemClickListener {

    void onLong(int position);
    void onClick(int position);
}
