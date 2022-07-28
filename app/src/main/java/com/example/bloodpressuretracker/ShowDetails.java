package com.example.bloodpressuretracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowDetails extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    //ovverinding the back button
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ShowDetails.this,UserList.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        String key=getIntent().getStringExtra("Key");
        String systole= getIntent().getStringExtra("Systole");
        String diastole= getIntent().getStringExtra("Diastole");
        String bpm= getIntent().getStringExtra("BPM");
        String date= getIntent().getStringExtra("Date");
        String comment=getIntent().getStringExtra("Comment");

        databaseReference= FirebaseDatabase.getInstance().getReference("users");

        TextView tsystole=findViewById(R.id.systole);
        TextView tdiastole=findViewById(R.id.diastole);
        TextView tbpm=findViewById(R.id.bpm);
        TextView tdate=findViewById(R.id.date);
        TextView tcomment=findViewById(R.id.comment);

        Button update,delete;
        update=findViewById(R.id.updatebtn);
        delete=findViewById(R.id.dltbtn);

        tsystole.setText("Systole: "+systole);
        tdiastole.setText("Diastole: "+diastole);
        tbpm.setText("BPM: "+bpm);
        tdate.setText("Date recorded: "+date);
        tcomment.setText("Comment: "+comment+" ");


        //update button onclicklistener
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShowDetails.this,Update.class);
                intent.putExtra("Key",key);
                /*String passKey="";
                Bundle bundle = new Bundle();
                bundle.putString(key,passKey);
                intent.putExtras(bundle);*/
                startActivity(intent);
                finish();
            }
        });

        //delete button onclicklistener
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference= FirebaseDatabase.getInstance().getReference("users").child(key);
                databaseReference.removeValue();
                startActivity(new Intent(ShowDetails.this,UserList.class));

                Toast.makeText(ShowDetails.this,key+" is deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }


}