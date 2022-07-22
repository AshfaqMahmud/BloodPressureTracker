package com.example.bloodpressuretracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    SQLiteDB sqLiteDB;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
//amitisagoodboy.
        FloatingActionButton myFab = findViewById(R.id.fabadd);
        add=findViewById(R.id.addbutton);
        sqLiteDB=new SQLiteDB(this);
        SQLiteDatabase sqLiteDatabase= sqLiteDB.getWritableDatabase();
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,InsertToDB.class);
                startActivity(intent);
            }
        });
    }
}