package com.example.taskapp.Preferences;

import static com.example.taskapp.R.drawable.profile_photo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.example.taskapp.ui.on_Boarding.BoardFragment;

public class Prefs {

    private SharedPreferences preferences;
    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }


    public void savedBoardStates() {
        preferences.edit().putBoolean("isBoardIsShown", true).apply();

    }


    public boolean isBoardShown() {
        return preferences.getBoolean("isBoardIsShown", false);
    }

    public void saveName(String name) {
        preferences.edit().putString("name", name).apply();

    }

    public String getName() {
        return preferences.getString("name", "");
    }

    public void saveAddress(String address) {
        preferences.edit().putString("address", address).apply();
    }

    public String getAddress() {
        return preferences.getString("address", "");
    }

    public void saveDate(String date) {
        preferences.edit().putString("date", date).apply();
    }

    public String getDate() {
        return preferences.getString("date", "");
    }

    public void savePhone(String phone) {
        preferences.edit().putString("phone", phone).apply();
    }

    public String getPhone() {
        return preferences.getString("phone", "");
    }

    public void saveEmail(String email) {
        preferences.edit().putString("email", email).apply();
    }

    public String getEmail() {
        return preferences.getString("email", "");
    }

    public void saveImageUri(Uri uri) {
        preferences.edit().putString("uri", uri.toString());
    }

    public Uri getImageUri() {
        return Uri.parse(preferences.getString("uri", ""));
    }


}
