package com.example.mussj.mynewrock;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference userRef = myRef.child("USERS");



    EditText username, name, ema,message;
    Button button, getButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        ema = findViewById(R.id.ema);
        message=findViewById(R.id.msg);
        button = findViewById(R.id.button);
        getButton = findViewById(R.id.get);

        // myRef.setValue("TEST");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myUserName = username.getText().toString().trim();
                String myName = name.getText().toString();
                String myEmail = ema.getText().toString().trim();
                String myMsg = message.getText().toString();

                HashMap<String, String> userMap = new HashMap<>();
                userMap.put("UserName", myUserName);
                userMap.put("Name", myName);
                userMap.put("Email", myEmail);
                userMap.put("Message",myMsg);
                userRef.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserGet.class);
                startActivity(intent);
            }
        });


    }
}