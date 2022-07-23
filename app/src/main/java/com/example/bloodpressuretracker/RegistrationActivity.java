package com.example.bloodpressuretracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference= database.getReferenceFromUrl("https://bptrt-4c5f4-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText name=findViewById(R.id.fullname);
        final EditText mail=findViewById(R.id.usermail);
        final EditText age=findViewById(R.id.userage);
        final EditText phone=findViewById(R.id.phone);
        final EditText pass=findViewById(R.id.password);
        final EditText cpass=findViewById(R.id.c_password);
        final Button btn1= findViewById(R.id.submit);
        final Button btn2= findViewById(R.id.already_user);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fname=name.getText().toString();
                final String email=mail.getText().toString();
                final String uage=age.getText().toString();
                final String phones=phone.getText().toString();
                final String pass1=pass.getText().toString();
                //final String pass2=cpass.getText().toString();

                if(fname.isEmpty()||email.isEmpty()||uage.isEmpty()||pass1.isEmpty()||phones.isEmpty())
                {
                    Toast.makeText(RegistrationActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    reference.child("users").child(phones).child("Name").setValue(fname);
                    reference.child("users").child(phones).child("Email").setValue(email);
                    reference.child("users").child(phones).child("Age").setValue(uage);
                    reference.child("users").child(phones).child("Phone").setValue(phones);
                    reference.child("users").child(phones).child("Password").setValue(pass1);

                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });

    }
}