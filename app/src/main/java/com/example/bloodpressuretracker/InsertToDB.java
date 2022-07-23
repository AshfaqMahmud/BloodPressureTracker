package com.example.bloodpressuretracker;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertToDB extends AppCompatActivity {
    SQLiteDB sqLiteDB;
    EditText systolE,diastolE,bpmE,cmntE,dateE;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_layout);
        systolE=findViewById(R.id.getsystol);
        diastolE=findViewById(R.id.getdiastol);
        bpmE=findViewById(R.id.getbpm);
        cmntE=findViewById(R.id.getcomment);
        add=findViewById(R.id.add);
        sqLiteDB=new SQLiteDB(this);
        SQLiteDatabase sqLiteDatabase=sqLiteDB.getWritableDatabase();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sys, dia, bpm, cmnt;
                sys = systolE.getText().toString();
                dia = diastolE.getText().toString();
                bpm = bpmE.getText().toString();
                cmnt = cmntE.getText().toString();
                long x = sqLiteDB.insertData(sys, dia, bpm, cmnt);
                if (x != -1) {
                    Toast.makeText(InsertToDB.this, "Success Insertion", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(InsertToDB.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
