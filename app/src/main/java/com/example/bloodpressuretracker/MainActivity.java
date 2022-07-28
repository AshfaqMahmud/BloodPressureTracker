package com.example.bloodpressuretracker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//uml
public class MainActivity extends AppCompatActivity {

    Button insert, show;
    EditText eSys,eDia,eBp,eCmnt;
    DatabaseReference databaseReference;
    @Override
    //uml
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert=findViewById(R.id.add);
        show=findViewById(R.id.view);
        eSys=findViewById(R.id.systole);
        eDia=findViewById(R.id.diastole);
        eBp=findViewById(R.id.bpm);
        eCmnt=findViewById(R.id.comment);

        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getInstance().format(calendar.getTime());
        TextView textViewDate=findViewById(R.id.showDate);
        textViewDate.setText(currentDate);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UserList.class));
                finish();
            }
        });
    }
//uml
    private void InsertData() {
        String usersys= eSys.getText().toString();
        String userdia= eDia.getText().toString();
        String userbp= eBp.getText().toString();
        String usercmn= eCmnt.getText().toString();
        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getInstance().format(calendar.getTime());
        int n1 = Integer.parseInt(usersys);
        int n2 = Integer.parseInt(userdia);
        int n3 = Integer.parseInt(userbp);
        String verdict="";
        if(usersys.isEmpty()||userdia.isEmpty()||userbp.isEmpty())
        {
            Toast.makeText(MainActivity.this,"Please Insert Values",Toast.LENGTH_SHORT).show();
        }
        else if(n1<90 || n1>140 )
        {
            Toast.makeText(MainActivity.this,"Systole should be in between 90 and 140",Toast.LENGTH_SHORT).show();
        }
        else if(n2<60 || n2>90)
        {
            Toast.makeText(MainActivity.this,"Diastole should be in between 60 and 90",Toast.LENGTH_SHORT).show();
        }
        else if(n3<50)
        {
            verdict="Too Low BPM";
        }

        else
        {
            String id= databaseReference.push().getKey();

            if(n1<=120 && n2<=80)
            {
                verdict = verdict +" Normal";
            }
            else
            {
                verdict = verdict+ " High Pressure";
            }

            User user= new User(usersys,userdia,userbp,usercmn,currentDate,verdict,id);
            databaseReference.child("users").child(id).setValue(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


    }
}