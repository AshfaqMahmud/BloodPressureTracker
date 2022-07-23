package com.example.bloodpressuretracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
    User user =list.get(position);
    holder.tsys.setText(user.getSys());
    holder.tdia.setText(user.getDia());
    holder.tbpm.setText(user.getBp());
    holder.tdate.setText(user.getDate());
    holder.tcmnt.setText(user.getCmnt());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tsys,tdia,tbpm,tcmnt,tdate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tsys= itemView.findViewById(R.id.tsys);
            tdia=itemView.findViewById(R.id.tdia);
            tbpm=itemView.findViewById(R.id.tbpm);
            tcmnt=itemView.findViewById(R.id.tcmnt);
            tdate=itemView.findViewById(R.id.tdate);
        }
    }
}
