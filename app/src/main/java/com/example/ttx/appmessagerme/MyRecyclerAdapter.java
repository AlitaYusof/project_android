package com.example.ttx.appmessagerme;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.Holder>{

    @NonNull
    @Override
//    public Holder onCreateViewHolder(@NonNull ViewGroup , int viewType)
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewGroup) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_confirm, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    //    public void onBindViewHolder(@NonNull Holder holder, int )
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textTitle.setText("CodeMobile");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDescription;

        public Holder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_order);
           // textDescription = itemView.findViewById(R.id.text_description);
        }

    }
    }

