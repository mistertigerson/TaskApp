package com.example.taskapp.ui.interfaces;

import android.view.View;

import com.example.taskapp.models.HomeModel;

public interface OnItemClickListener {

    void onLong(int position);
    void onClick(int position);
}
