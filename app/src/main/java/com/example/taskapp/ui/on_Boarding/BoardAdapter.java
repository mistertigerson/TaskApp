package com.example.taskapp.ui.on_Boarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.taskapp.R;
import com.example.taskapp.ui.interfaces.OnItemListener;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    OnItemListener listener;
    GetPosition posListener;

    public void setListener(OnItemListener listener){
        this.listener = listener;
    }
    public void setPosListener(GetPosition posListener){
        this.posListener =  posListener;
    }

    private String[] deskText = new String[]{"Fast ", "Security", "Smart"};
    private String[] titles = new String[]{"Very","Absolutely" , "Amazing"};
    private int[] images = new int[]{R.raw.rocket,R.raw.certified, R.raw.brain_bulb_charging};

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
        LottieAnimationView animationView;
        Button btn, btn1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title);
            desk = itemView.findViewById(R.id.textDesc);
            animationView = itemView.findViewById(R.id.animation);
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
            animationView.setAnimation(images[position]);
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
