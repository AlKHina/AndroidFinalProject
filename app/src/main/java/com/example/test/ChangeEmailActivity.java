package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ChangeEmailActivity extends AppCompatActivity {

    EditText currentEmail, newEmail;
    TextView forgotEmail;
    Button changeEmail;
    private AuthClass auth;
    private  String currentNum, newNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        auth = new AuthClass(getApplicationContext());

        currentEmail = findViewById(R.id.currentEmail);
        newEmail = findViewById(R.id.newEmail);
        forgotEmail = findViewById(R.id.forgotEmail);
        changeEmail = findViewById(R.id.changeEmail);


        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean confirmEmail = true;
                currentEmail = currentEmail.getText().toString();
                newEmail = newEmail.getText().toString();

                if (currentNum.isEmpty()) {
                    confirmEmail = false;
                    currentEmail.setError("Это поле осталось пустым");
                } else if (newNum.isEmpty()) {
                    confirmEmail = false;
                    newEmail.setError("Это поле осталось пустым");
                }
                    if (confirmEmail) {
                        Query query = FirebaseDatabase.getInstance().getReference().child("accounts").orderByChild("login").equalTo(confirmEmail);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChildren()) {
                                    UserClass account = new UserClass();
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        account = snapshot1.getValue(UserClass.class);
                                    }

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        }
                    }
                });
            }
        }




