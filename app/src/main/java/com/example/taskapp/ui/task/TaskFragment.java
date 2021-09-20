package com.example.taskapp.ui.task;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.taskapp.App;
import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentTaskBinding;
import com.example.taskapp.models.Task;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TaskFragment extends Fragment {

    private Button button;
    private FragmentTaskBinding binding;
    private Task task;
    private RecyclerView.ViewHolder viewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTaskBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        task = (Task) requireArguments().getSerializable("task");
        if (task != null){
            binding.editText.setText(task.getName());

        }
        button = view.findViewById(R.id.save_btn);

        button.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                save();
                close();
            }
        });
    }

    private void save() {
        String text = binding.editText.getText().toString();

        if (text.isEmpty()) return;

        if (task == null){
            Date currentTime = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat( "HH:mm dd-MMMM-yyyy");
            String strDate = dateFormat.format(currentTime);

            task = new Task(text, strDate);
            App.getAppDataBase().taskDao().insert( task);
            saveFirestore(task);

        }else {
            if (!task.getName().equals(text)){
                task.setName(text);
                App.getAppDataBase().taskDao().update(task);
            }


        }


        Bundle bundle = new Bundle();
        bundle.putSerializable("task ", task);
        getParentFragmentManager().setFragmentResult("homeModel1", bundle);
        close();


    }

    private void saveFirestore(Task task) {
        FirebaseFirestore.getInstance().
                collection("users").
                add(task).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(@NonNull DocumentReference documentReference) {
                        Log.e(TAG, "onSuccess: " + documentReference.getId() );
                    }
                });

    }

    public void close(){
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

}