package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;

public class AddingPublicationsActivity extends AppCompatActivity {
    AlertDialog ad;
    private Button button_camera,button_hareleya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_publications);

        button_camera = findViewById(R.id.ButtonDialogCamera);
        button_hareleya = findViewById(R.id.ButtonDialogHareleya);

        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View alertView = getLayoutInflater().inflate(R.layout.activity_adding_publications,null);