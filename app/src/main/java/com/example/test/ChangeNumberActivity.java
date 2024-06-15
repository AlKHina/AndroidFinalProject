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

public class ChangeNumberActivity extends AppCompatActivity {

    EditText currentNumber, newNumber;
    TextView forgotNumber;
    Button changeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_number);

        currentNumber = findViewById(R.id.currentNumber);
        newNumber = findViewById(R.id.newNumber);
        forgotNumber = findViewById(R.id.forgotNumber);
        changeNumber = findViewById(R.id.changeNumber);

        changeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentNum = currentNumber.getText().toString();
                String newNum = newNumber.getText().toString();


                if (newNum.equals(confirmNum)) {
                    Toast.makeText(ChangeNumberActivity.this, "Номер телефона успешно изменен", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgotNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не верный номер телефона ?", Toast.LENGTH_SHORT).show();
            }
        });
    }
}