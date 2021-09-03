package com.example.taskapp.ui.task;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.taskapp.R;
import com.example.taskapp.models.HomeModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskFragment extends Fragment {

    private Button button;
    private EditText editText;
    private RecyclerView.ViewHolder viewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
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
        String text = editText.getText().toString();

        if (text.isEmpty()) return;
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat( "HH:mm dd-MMMM-yyyy");
        String strDate = dateFormat.format(currentTime);
        Bundle bundle = new Bundle();
        HomeModel homeModel = new HomeModel(text, strDate);
        bundle.putSerializable("homeModel", homeModel);
        getParentFragmentManager().setFragmentResult("homeModel1", bundle);

    }

    public void close(){
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

}