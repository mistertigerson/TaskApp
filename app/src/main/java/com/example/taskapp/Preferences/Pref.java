package com.example.taskapp.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
public class Pref {
    private SharedPreferences preferences;

    public Pref(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }
}
