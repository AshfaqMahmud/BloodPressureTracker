package com.example.bloodpressuretracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    SQLiteDB sqLiteDB;
    Button add,show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.addbutton);
        show=findViewById(R.id.showbutton);
        sqLiteDB=new SQLiteDB(this);
        SQLiteDatabase sqLiteDatabase= sqLiteDB.getWritableDatabase();
        //add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,InsertToDB.class);
                startActivity(intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,FetchData.class);
                startActivity(intent);
            }
        });

    }
}