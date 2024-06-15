package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText currentPassword, newPassword, confirmNewPassword;
    TextView forgotPassword;
    Button changePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPassword = findViewById(R.id.currentPasswordET);
        newPassword = findViewById(R.id.newPasswordET);
        confirmNewPassword = findViewById(R.id.confirmNewPasswordET);
        forgotPassword = findViewById(R.id.forgotPasswordTV);
        changePasswordButton = findViewById(R.id.changePasswordButton);

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPass = currentPassword.getText().toString();
                String newPass = newPassword.getText().toString();
                String confirmPass = confirmNewPassword.getText().toString();

                if (newPass.equals(confirmPass)) {
                    Toast.makeText(ChangePasswordActivity.this, "Пароль успешно изменен", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Новый пароль и его подтверждение не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Забыли пароль?", Toast.LENGTH_SHORT).show();
            }
        });
    }
}