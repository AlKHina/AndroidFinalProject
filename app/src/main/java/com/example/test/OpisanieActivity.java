package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OpisanieActivity extends AppCompatActivity {

    ImageView imageViewanimalfoto;
    TextView textViewnazvanie;
    TextView textViewopisanie;
    TextView textViewprice;
    TextView textViewnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opisanie);

        imageViewanimalfoto = findViewById(R.id.imageViewanimalfoto);
        textViewnazvanie = findViewById(R.id.textViewnazvanie);
        textViewopisanie = findViewById(R.id.textViewopisanie);
        textViewprice = findViewById(R.id.textViewprice);
        textViewnumber = findViewById(R.id.textViewnumber);

        Intent returnIntent = getIntent();
        String fotoString = returnIntent.getStringExtra("foto");
        textViewnazvanie.setText(returnIntent.getStringExtra("name"));
        textViewopisanie.setText(returnIntent.getStringExtra("opisanie"));
        textViewprice.setText(returnIntent.getStringExtra("price"));
        byte[] decodedString = android.util.Base64.decode( fotoString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
        imageViewanimalfoto.setImageBitmap(decodedByte);
    }
}