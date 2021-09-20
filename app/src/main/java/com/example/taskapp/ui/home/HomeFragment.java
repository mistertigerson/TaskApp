package com.example.taskapp.ui.home;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.taskapp.App;
import com.example.taskapp.Preferences.Prefs;
import com.example.taskapp.databinding.FragmentTaskBinding;
import com.example.taskapp.models.Task;
import com.example.taskapp.room.TaskDao;
import com.example.taskapp.ui.interfaces.OnItemClickListener;
import com.example.taskapp.R;
import com.example.taskapp.adapters.HomeAdapter;
import com.example.taskapp.databinding.FragmentHomeBinding;
import com.example.taskapp.ui.task.TaskFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FloatingActionButton fab;
    private HomeAdapter adapter;
    private Prefs prefs;
    private int pos;
    Bundle bundle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle();
        prefs = new Prefs(requireContext());
        adapter = new HomeAdapter();
        adapter.addItem(App.getAppDataBase().taskDao().getAll());

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        initRecycler();

        adapter.setListener(new OnItemClickListener() {


            @Override
            public void onLong(int position) {
                Task task = adapter.getItem(position);
                showAlert(task);
            }

            @Override
            public void onClick(int position) {
                Task task = adapter.getItem(position);
                openTaskFragment(task);
                pos = position;
            }
        });



        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            openTaskFragment(null);
            pos = -1;


        });


        getParentFragmentManager().setFragmentResultListener("homeModel1", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Task task = (Task) result.getSerializable("task");
                if (pos == -1){
                    adapter.addItem(task);

                }else {
                    adapter.setItem(pos, task);
                    Log.e(TAG, "onFragmentResult:flsdkajfldsajgldsjlkgjsdlakjglsg " + pos);

                }
            }
        });
    }

    private void showAlert(Task task){
        new AlertDialog.Builder(requireContext())
                .setMessage("вы хотите удалить?")
                .setNegativeButton("отмена", null )
                .setPositiveButton("да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getAppDataBase().taskDao().delete(task);
                        adapter.removeItem(task);

                    }
                }).show();
    }



    private void initRecycler() {
        binding.homeRecycler.setAdapter(adapter);

    }


    public void openTaskFragment(Task task) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("task", task);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.taskFragment, bundle);
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.sort:
                adapter.setList(App.getAppDataBase().taskDao().getAllByTitle());
                Log.e(TAG, "onOptionsItemSelected:++++++++++++++ " + App.getAppDataBase().taskDao().getAllByTitle());
                binding.homeRecycler.setAdapter(adapter);
                return true;
        }
        return false;
    }

    private void deleteTask(Task task) {
        App.getAppDataBase().taskDao().delete(task);
    }
}