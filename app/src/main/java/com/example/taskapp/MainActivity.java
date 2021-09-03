package com.example.taskapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.taskapp.Preferences.Preferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.taskapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ActivityMainBinding binding;
    private Button btn;
    public NavDestination destination;
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.profileFragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        contextOfApplication = getApplicationContext();
        Preferences preferences = new Preferences(this);
        if (preferences.isBoardShown()){
            navController.navigate(R.id.boardFragment);
        }





        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                List<Integer> tabFragments = new ArrayList<>();
                tabFragments.add(R.id.navigation_home);
                tabFragments.add(R.id.navigation_dashboard);
                tabFragments.add(R.id.navigation_notifications);
                tabFragments.add(R.id.profileFragment);

                if (tabFragments.contains(destination.getId())){
                    navView.setVisibility(View.VISIBLE);
                }
                else {
                    navView.setVisibility(View.GONE);
                }
                if(destination.getId() == R.id.boardFragment){
                    getSupportActionBar().hide();
                }else {
                    getSupportActionBar().show();
                }
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();

    }
}