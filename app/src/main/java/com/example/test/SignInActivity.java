package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    private EditText EditTextEmai, EditTextPassword;
    private LinearLayout ButtonBtnNext;
    private AuthClass auth;
    private  String login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = new AuthClass(getApplicationContext());

        defineViews();
        assignListeners();
    }

    private void assignListeners() {
        ButtonBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isEverythingOK = true;

                 login = EditTextEmai.getText().toString();
                 password = EditTextPassword.getText().toString();

                if (login.isEmpty()) {
                    isEverythingOK = false;
                    EditTextEmai.setError("Это поле осталось пустым");
                } else if (password.isEmpty()) {
                    isEverythingOK = false;
                    EditTextPassword.setError("Это поле осталось пустым");
                } else if (!password.matches("\\w+")) {
                    isEverythingOK = false;
                    EditTextPassword.setError("Пароль не совпадает");
                } else if (!login.matches("\\w+")) {
                    isEverythingOK = false;
                    EditTextEmai.setError("Логин не совпадает");
                }
                if (isEverythingOK) {

                    Query query = FirebaseDatabase.getInstance().getReference().child("accounts").orderByChild("login").equalTo(login);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChildren()) {
                                UserClass account = new UserClass();
                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                    account = snapshot1.getValue(UserClass.class);
                                }
                                if (password.equals(account.getPass())) {
                                    auth.setUser(account);
                                    auth.setUsername(account.getNick());
                                    auth.setKey(account.getKey());

                                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    EditTextPassword.setError("Пароль не правильный");
                                }
                            } else {
                                EditTextEmai.setError("Tакого логина нет!");
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
        EditTextEmai = findViewById(R.id.EditTextEmail);
        EditTextPassword = findViewById(R.id.EditTextPassword);
        ButtonBtnNext = findViewById(R.id.ButtonBtnNext);
    }
}
