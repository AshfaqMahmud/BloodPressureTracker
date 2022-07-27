package com.example.bloodpressuretracker;

import android.content.Context;
import android.graphics.Color;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<User> list;


    public MyAdapter(Context context, ArrayList<User> list, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list = list;
        //this.listener=listener;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
    User user =list.get(position);
    holder.tsys.setText(user.getSys());
    holder.tdia.setText(user.getDia());
    holder.tbpm.setText(user.getBp());
    holder.tdate.setText(user.getDate());
    //holder.tcmnt.setText(user.getCmnt());
    //holder.key.setText(user.getKey());
        holder.verdict.setText(user.getVerdict());
        //if(!Objects.equals(user.getVerdict(), "Normal"))
        //{
          //  holder.cv.setBackgroundColor(Color.parseColor("#FF5349"));
        //}

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface ItemClickListener{
        void onItemClick(User details);
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tsys,tdia,tbpm,tcmnt,tdate,verdict;
        //LinearLayout row_linearlayout;
        //CardView cv = (CardView) itemView.findViewById(R.id.card);
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tsys= itemView.findViewById(R.id.tsys);
            tdia=itemView.findViewById(R.id.tdia);
            tbpm=itemView.findViewById(R.id.tbpm);
            //tcmnt=itemView.findViewById(R.id.tcmnt);
            tdate=itemView.findViewById(R.id.tdate);
            //key=itemView.findViewById(R.id.key);
            verdict=itemView.findViewById(R.id.verdict);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null)
                    {
                        int pos =getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
