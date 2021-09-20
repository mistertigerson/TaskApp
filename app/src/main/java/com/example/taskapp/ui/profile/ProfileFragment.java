package com.example.taskapp.ui.profile;

import static com.example.taskapp.R.drawable.profile_photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskapp.MainActivity;
import com.example.taskapp.Preferences.Prefs;
import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Prefs prefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(requireContext());
        setHasOptionsMenu(true);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTypeInput();

        binding.galleryIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createImage();

            }
        });


    }

    private void setTypeInput() {
        binding.inputName.setText(prefs.getName());
        binding.inputAddress.setText(prefs.getAddress());
        binding.inputDate.setText(prefs.getDate());
        binding.inputPhone.setText(prefs.getPhone());
        binding.inputEmail.setText(prefs.getEmail());
        binding.galleryIv.setImageURI(prefs.getImageUri());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.clear_item:
                binding.inputName.setText("");
                binding.inputEmail.setText("");
                binding.inputPhone.setText("");
                binding.inputDate.setText("");
                binding.inputAddress.setText("");
                return true;
        }
        return false;
    }


    @Override
    public void onStop() {
        super.onStop();
        saveInput();

    }

    private void saveInput() {
        prefs.saveName(binding.inputName.getText().toString());
        prefs.saveAddress(binding.inputAddress.getText().toString());
        prefs.saveDate(binding.inputDate.getText().toString());
        prefs.savePhone(binding.inputPhone.getText().toString());
        prefs.saveEmail(binding.inputEmail.getText().toString());


    }


    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Uri uri = result.getData().getData();
                        binding.galleryIv.setImageURI(uri);
                        prefs.saveImageUri(uri);

                    }
                }

            });

    public void createImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);

    }


}