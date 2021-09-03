package com.example.taskapp.Preferences;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskapp.Preferences.PersistantStorage;
import com.example.taskapp.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private int SELECT_PICTURE = 200;
    public static final String APP_PREFERENCES1 = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Username";
    public static final String APP_PREFERENCES_AGE = "Age";
    EditText editText;
    public String username;
    public SharedPreferences prefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        binding.saveBtn.setOnClickListener(v -> {
            createPrefs();
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        prefs = context.getSharedPreferences(APP_PREFERENCES1, Context.MODE_PRIVATE);
        username  = prefs.getString(APP_PREFERENCES_NAME, "true");


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        binding.galleryIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createImage();

            }
        });

    }

    private void createPrefs() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(APP_PREFERENCES_NAME, binding.textInput1.getText().toString()).apply();
        binding.usernameTv.setText(username);
    }


    public void createImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("result ", "=" + requestCode);
        Uri selectedImageUri = data.getData();
        Log.e("image", "=" + selectedImageUri);
        if (null != selectedImageUri) {
            binding.galleryIv.setImageURI(selectedImageUri);
        }
    }
}