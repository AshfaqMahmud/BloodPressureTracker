package com.example.bloodpressuretracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Update extends AppCompatActivity {
    //declareing variables
    Button update,view;
    EditText eSys,eDia,eBp,eCmnt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        String key=getIntent().getStringExtra("Key");       //getting key form showdetails class

        update=findViewById(R.id.update);
        view=findViewById(R.id.view);
        eSys=findViewById(R.id.systole);
        eDia=findViewById(R.id.diastole);
        eBp=findViewById(R.id.bpm);
        eCmnt=findViewById(R.id.comment);

        //getting realtime date
        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getInstance().format(calendar.getTime());
        TextView textViewDate=findViewById(R.id.showDate);
        textViewDate.setText(currentDate);
        databaseReference= FirebaseDatabase.getInstance().getReference("users");

        //update button onclicklistener
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(key);
                Toast.makeText(Update.this,"Key is "+key,Toast.LENGTH_SHORT).show();

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Update.this,UserList.class));
                finish();
            }
        });
    }

    private void updateData(String key) {
        String usersys= eSys.getText().toString();
        String userdia= eDia.getText().toString();
        String userbp= eBp.getText().toString();
        String usercmn= eCmnt.getText().toString();
        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getInstance().format(calendar.getTime());

        String id= key;

        User user= new User(usersys,userdia,userbp,usercmn,currentDate);
        databaseReference.child(id).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Update.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
