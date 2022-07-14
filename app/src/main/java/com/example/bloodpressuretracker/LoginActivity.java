package com.example.bloodpressuretracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    FirebaseDatabase rootnode =FirebaseDatabase.getInstance();
    DatabaseReference reference= rootnode.getReferenceFromUrl("https://blood-pressure-tracker-1d80a-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText mail=findViewById(R.id.usermail);
        final EditText pass=findViewById(R.id.password);
        final CheckBox chk=findViewById(R.id.rememberMe);
        final Button btn1= findViewById(R.id.login);
        final Button btn2= findViewById(R.id.register);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email=mail.getText().toString();
                final String pass1=pass.getText().toString();

                if (email.isEmpty()||pass1.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Fill Information Please", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(email))
                            {
                                final String getpass=snapshot.child(email).child("Password").getValue(String.class);
                                if(getpass.equals(pass1))
                                {
                                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Invalid Usermail", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}