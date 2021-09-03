package com.example.taskapp.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }



    public void savedBoardStates(){
        preferences.edit().putBoolean("isBoardIsShown", true).apply();

    }
    public boolean isBoardShown(){
        return preferences.getBoolean("isBoardIsShown", false);
    }
}
