package com.example.taskapp.ui.onBoarding;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.ui.OnItemListener;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    OnItemListener listener;
    GetPosition posListener;

    public void setListener(OnItemListener listener){
        this.listener = listener;
    }
    public void setPosListener(GetPosition posListener){
        this.posListener =  posListener;
    }

    private String[] deskText = new String[]{"салам ", "вместе", "будем"};
    private String[] titles = new String[]{"Ребята","давайте" , "жить дружно"};
    private int[] images = new int[]{R.drawable.first, R.drawable.second_tom, R.drawable.thirth_tom};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_board, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onItem(position);
        holder.onBind(position);
//        posListener.getPosition(position);
    }

    public void onItem(int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }



    public class ViewHolder extends RecyclerView.ViewHolder  {



        TextView textTitle, desk;
        ImageView imageView;
        Button btn, btn1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title);
            desk = itemView.findViewById(R.id.textDesc);
            imageView = itemView.findViewById(R.id.onBoarding_iv);
            btn = itemView.findViewById(R.id.start_btn);
            btn1 = itemView.findViewById(R.id.skip_btn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });


        }


        public void onBind(int position) {
            desk.setText(deskText[position]);
            textTitle.setText(titles[position]);
            imageView.setImageResource(images[position]);
            posListener.getPosition(position);
            Log.e("test234", "onBind: how many time is called "+ position);
            if (position==2){
                btn.setVisibility(View.VISIBLE);

            }else {
                btn.setVisibility(View.GONE);
            }

        }


    }

    interface GetPosition {
        void getPosition(int position);

    }

}
