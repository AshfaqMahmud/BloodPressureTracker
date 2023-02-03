package com.example.bloodpressuretracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserList extends AppCompatActivity implements RecyclerViewInterface {
    //Declaring variables
    RecyclerView recyclerView;
    ArrayList<User> list;
    DatabaseReference databaseReference;
    FirebaseUser mAuth;
    MyAdapter myAdapter;

    @Override
    //ovverinding the back button
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserList.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fab_user = findViewById(R.id.fab_user);
        recyclerView = findViewById(R.id.recycler);
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mAuth.getUid();
        //getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        list = new ArrayList<>();

        //bringing list in the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, list, this);
        recyclerView.setAdapter(myAdapter);


        databaseReference.child(uid).child("Records").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String key = dataSnapshot.getKey();

                    String systol = dataSnapshot.child("sys").getValue(String.class);
                    String diastol = dataSnapshot.child("dia").getValue(String.class);
                    String bpm = dataSnapshot.child("bp").getValue(String.class);
                    String date = dataSnapshot.child("date").getValue(String.class);
                    String cmnt = dataSnapshot.child("cmnt").getValue(String.class);
                    String verdict = dataSnapshot.child("verdict").getValue(String.class);
                    //Toast.makeText(UserList.this,"ADEED",Toast.LENGTH_SHORT).show();
                    User user = new User(systol, diastol, bpm, cmnt, date, verdict, key);
                    list.add(user);                                     //inserting value in the firebase
                }
                myAdapter.notifyDataSetChanged();                       //adapter noitified for data change
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserList.this, " " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        fab_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserList.this, ProfilePage.class);
                startActivity(intent);
            }
        });


    }

    //clickable items on the recyclerview
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(UserList.this, ShowDetails.class);
        intent.putExtra("Key", list.get(position).getKey());
        intent.putExtra("Systole", list.get(position).getSys());
        intent.putExtra("Diastole", list.get(position).getDia());
        intent.putExtra("BPM", list.get(position).getBp());
        intent.putExtra("Date", list.get(position).getDate());
        intent.putExtra("Comment", list.get(position).getCmnt());
        startActivity(intent);
        finish();

    }
}