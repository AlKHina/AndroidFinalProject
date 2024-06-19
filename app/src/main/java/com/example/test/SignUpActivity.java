package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextNick, editTextLogin, editTextPassword, editTextRepeatPassword;
    private LinearLayout buttonCreateAkk;
    private TextView textViewEnterAkk;
    private AuthClass auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = new AuthClass(getApplicationContext());

        defineViews();
        assignListeners();
    }

    private void assignListeners() {
        textViewEnterAkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonCreateAkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEverythingOK = true;

                String nick = editTextNick.getText().toString();
                String email = editTextLogin.getText().toString();
                String pass = editTextPassword.getText().toString();
                String number ="";
                String ava = "";
                String repeatPassword = editTextRepeatPassword.getText().toString();

                if (nick.isEmpty()) {
                    isEverythingOK = false;
                    editTextNick.setError("Это поле осталось пустым");
                } else if (email.isEmpty()) {
                    isEverythingOK = false;
                    editTextLogin.setError("Это поле осталось пустым");
                } else if (pass.isEmpty()) {
                    isEverythingOK = false;
                    editTextPassword.setError("Это поле осталось пустым");
                } else if (repeatPassword.isEmpty()) {
                    isEverythingOK = false;
                    editTextRepeatPassword.setError("Это поле осталось пустым");
                } else if (pass.length() < 8) {
                    isEverythingOK = false;
                    editTextPassword.setError("Пароль должен состоять не менее, чем из 8 символов");
                } else if (!pass.matches("\\w+")) {
                    isEverythingOK = false;
                    editTextPassword.setError("Пароль должен состоять только из латинских букв, символов нижнего подчёркивания или цифр");
                } else if (!repeatPassword.equals(pass)) {
                    isEverythingOK = false;
                    editTextRepeatPassword.setError("Пароли не совпадают");
                }

                if (isEverythingOK) {
                    Query query = FirebaseDatabase.getInstance().getReference().child("accounts").orderByChild("login").equalTo(email);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChildren()) {
                                editTextLogin.setError("Аккаунт с таким логином уже существует");
                            } else {
                                DatabaseReference accounts = FirebaseDatabase.getInstance().getReference().child("accounts");
                                String key = accounts.push().getKey();
                                UserClass user = new UserClass(key, nick, email, number,pass,ava);
                                accounts.child(key).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        auth.setUsername(nick);
                                        auth.setUser(user);
                                        auth.setKey(key);
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
    }

    private void defineViews() {
        editTextNick = findViewById(R.id.editTextNick);
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword);
        buttonCreateAkk = findViewById(R.id.buttonCreateAkk);
        textViewEnterAkk = findViewById(R.id.textViewEnterAkk);
    }
}