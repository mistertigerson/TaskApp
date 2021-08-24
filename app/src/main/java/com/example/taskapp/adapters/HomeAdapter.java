package com.example.taskapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.OnItemClickListener;
import com.example.taskapp.R;
import com.example.taskapp.models.HomeModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private int number = 0;


    private ArrayList<HomeModel> list = new ArrayList<>();

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


        if(position%2==0){
            holder.itemView.setBackgroundColor(Color.DKGRAY);

        }
        else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public void addItem(HomeModel homeModel) {
        list.add(homeModel);
        notifyItemInserted(list.indexOf(homeModel));
        number++;

    }

    public void removeItem(int position) {
        this.list.remove(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {



        private TextView name;
        private TextView time;

        public ViewHolder(@NonNull View itemView)  {
            super(itemView);

            itemView.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition());
            });

            itemView.setOnLongClickListener(v -> {
                listener.onLong(getAdapterPosition());
                return true;
            });


            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
        }


        public void onBind(HomeModel homeModel) {

            name.setText(homeModel.getName());
            time.setText(homeModel.getTime());



        }

    }
}
