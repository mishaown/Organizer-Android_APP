package com.example.class_organizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {
    private Context context;
    ArrayList Class_name, Class_des, Class_ID, Class_Date;

    CustomAdaptor(Context context,ArrayList Class_ID, ArrayList Class_name, ArrayList Class_des, ArrayList Class_Date) {
        this.context = context;
        this.Class_ID = Class_ID;
        this.Class_name = Class_name;
        this.Class_des = Class_des;
        this.Class_Date = Class_Date;
    }


    @NonNull
    @Override
    public CustomAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.class_items, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdaptor.MyViewHolder holder, final int position) {
        holder.Class_ID.setText(String.valueOf(Class_ID.get(position)));
        holder.Class_name.setText(String.valueOf(Class_name.get(position)));
        holder.Class_des.setText(String.valueOf(Class_des.get(position)));
        holder.Class_Date.setText(String.valueOf(Class_Date.get(position)));

    }

    @Override
    public int getItemCount() {
        return Class_ID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Class_name, Class_des, Class_ID, Class_Date;
        LinearLayout class_items;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Class_ID = itemView.findViewById(R.id.Class_ID);
            Class_name = itemView.findViewById(R.id.Class_name);
            Class_des = itemView.findViewById(R.id.Class_des);
            Class_Date = itemView.findViewById(R.id.Class_Date);
            class_items = itemView.findViewById(R.id.class_items);
        }
    }
}
