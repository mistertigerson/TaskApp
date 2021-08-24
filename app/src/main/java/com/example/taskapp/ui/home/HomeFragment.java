package com.example.taskapp.ui.home;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.taskapp.OnItemClickListener;
import com.example.taskapp.R;
import com.example.taskapp.adapters.HomeAdapter;
import com.example.taskapp.databinding.FragmentHomeBinding;
import com.example.taskapp.models.HomeModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FloatingActionButton fab;
    private HomeAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeAdapter();

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        createList();
        initRecycler();
        adapter.setListener(new OnItemClickListener() {

            @Override
            public void onLong(int position) {
                new AlertDialog.Builder(requireActivity())
                        .setMessage("delete?")
                        .setPositiveButton("YES", (dialog, which) -> {
                            adapter.removeItem(position);
                        })
                        .setNegativeButton("NO",null)
                        .show();

            }

            @Override
            public void onClick(int position) {
                new AlertDialog.Builder(requireActivity())
                        .setMessage("нажата").show();
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            openTaskFragment();


        });


        getParentFragmentManager().setFragmentResultListener("homeModel1", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                HomeModel homeModel = (HomeModel) result.getSerializable("homeModel");
                Log.e("Home", "text = " + homeModel.getName());
                adapter.addItem(homeModel);


            }


        });


    }

    private void initRecycler() {
        binding.homeRecycler.setAdapter(adapter);

    }


    public void openTaskFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.taskFragment);
    }
}