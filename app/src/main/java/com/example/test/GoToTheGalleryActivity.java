package com.example.test;

import static androidx.activity.result.contract.ActivityResultContracts.*;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GoToTheGalleryActivity extends AppCompatActivity {
    val pickMedia = registerForActivityResult(PickVisualMedia())

    {
        uri ->
        if (uri != null) {
        } else {
        }
    }
    lateinit var ButtonDialogGallery: Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_the_gallery);

    }
}
