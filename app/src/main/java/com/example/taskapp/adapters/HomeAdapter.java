package com.example.taskapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.App;
import com.example.taskapp.Preferences.Prefs;
import com.example.taskapp.room.TaskDao;
import com.example.taskapp.ui.interfaces.OnItemClickListener;
import com.example.taskapp.R;
import com.example.taskapp.models.Task;
import com.example.taskapp.ui.interfaces.OnItemListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Task> list = new ArrayList<>();

    public void setList(List<Task> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(List<Task> tasks) {
        list.addAll(tasks);
        notifyDataSetChanged();

    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.DKGRAY);

        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void  addItem(Task task) {
        list.add(0, task);
        notifyItemInserted(list.size());
    }

    public void setItem(int pos, Task task ){
        list.set(pos, task);
        notifyItemChanged(pos, task);

    }


    public void removeItem(Task task) {
        int index = list.indexOf(task);
        this.list.remove(task);
        notifyItemRemoved(index );
    }

    public Task getItem(int position) {
        return list.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView name;
        private TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLong(getAdapterPosition());
                    return true ;
                }
            });



        }


        public void onBind(Task task) {
            name.setText(task.getName());
            time.setText(task.getTime());

        }

    }
}
