package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    LinearLayout arrow_email, arrow_password, arrow_number, arrow_publications;
    TextView change_nick;
    ImageView change_ava;
    AlertDialog dialog;
    AuthClass auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        auth = new AuthClass(getApplicationContext());

        change_ava = findViewById(R.id.change_ava);
        arrow_email = findViewById(R.id.arrow_email);
        arrow_password = findViewById(R.id.arrow_password);
        arrow_number = findViewById(R.id.arrow_number);
        arrow_publications = findViewById(R.id.arrow_publications);
        change_nick = findViewById(R.id.change_nick);

        change_nick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.alert_dialog_nick, null);
                EditText nameEdetText = alertView.findViewById(R.id.newNick);
                Button okButton = alertView.findViewById(R.id.changeNickOK);
                String curentName = auth.getUsername();
                nameEdetText.setText(curentName);
                Button cancellationButton = alertView.findViewById(R.id.changeNickCancellation);
                cancellationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (nameEdetText.getText().toString().isEmpty()) {
                            nameEdetText.setError("введите имя");
                        } else {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("accounts")
                                    .child(auth.getKey())
                                    .child("nick")
                                    .setValue(nameEdetText.getText().toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            Toast.makeText(UserActivity.this, " успешно изменен", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                });
                builder.setView(alertView);
                dialog = builder.create();
                dialog.show();

            }
        });
        arrow_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.alert_dialog_email, null);
                EditText emailEdetText = alertView.findViewById(R.id.newEmail);
                Button okButton = alertView.findViewById(R.id.changeEmailOK);
                String curentEmail = auth.getUser().getEmail();
                emailEdetText.setText(curentEmail);
                Button cancellationButton = alertView.findViewById(R.id.changeEmailCancellation);
                cancellationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (emailEdetText.getText().toString().isEmpty()) {
                            emailEdetText.setError("Введите email");
                        } else {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("accounts")
                                    .child(auth.getKey())
                                    .child("email")
                                    .setValue(emailEdetText.getText().toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            Toast.makeText(UserActivity.this, "email успешно изменен", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                });
                builder.setView(alertView);
                dialog = builder.create();
                dialog.show();

            }
        });
        arrow_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.alert_dialog_password, null);
                EditText passwordEdetText = alertView.findViewById(R.id.newPassword);
                Button okButton = alertView.findViewById(R.id.changePasswordOK);
                String curentEmail = auth.getUser().getEmail();
                passwordEdetText.setText(curentEmail);
                Button cancellationButton = alertView.findViewById(R.id.changePasswordCancellation);
                cancellationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (passwordEdetText.getText().toString().isEmpty()) {
                            passwordEdetText.setError("Введите email");
                        } else {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("accounts")
                                    .child(auth.getKey())
                                    .child("pass")
                                    .setValue(passwordEdetText.getText().toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            Toast.makeText(UserActivity.this, "email успешно изменен", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                });
                builder.setView(alertView);
                dialog = builder.create();
                dialog.show();

            }
        });
        arrow_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.alert_dialog_email, null);
                EditText emailEdetText = alertView.findViewById(R.id.newEmail);
                Button okButton = alertView.findViewById(R.id.changeEmailOK);
                String curentEmail = auth.getUser().getEmail();
                emailEdetText.setText(curentEmail);
                Button cancellationButton = alertView.findViewById(R.id.changeEmailCancellation);
                cancellationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (emailEdetText.getText().toString().isEmpty()) {
                            emailEdetText.setError("Введите email");
                        } else {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("accounts")
                                    .child(auth.getKey())
                                    .child("number")
                                    .setValue(emailEdetText.getText().toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            Toast.makeText(UserActivity.this, "email успешно изменен", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                });
                builder.setView(alertView);
                dialog = builder.create();
                dialog.show();

            }
}