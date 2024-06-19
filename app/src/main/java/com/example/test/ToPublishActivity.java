package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ToPublishActivity extends AppCompatActivity {

    private EditText EditTextNameCar,EditTextDescriptionCar,EditTextPriceCar,EditTextNumber,EditTextShortDescriptionCar;
    private ImageView photo;
    private LinearLayout ButtonPublish;
    private AuthClass auth;
    DatabaseReference db;
    String fotoString;
    DatabaseReference publications;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_publish);

        auth = new AuthClass(getApplicationContext());
        db = FirebaseDatabase.getInstance().getReference();
        publications = db.child("publications");

        photo = findViewById(R.id.ImageViewPhoto);
        ButtonPublish = findViewById(R.id.ButtonPublish);
        EditTextNameCar = findViewById(R.id.EditTextNameCar);
        EditTextDescriptionCar = findViewById(R.id.EditTextDescriptionCar);
        EditTextPriceCar = findViewById(R.id.EditTextPriceCar);
        EditTextNumber = findViewById(R.id.EditTextNumber);
        EditTextShortDescriptionCar = findViewById(R.id.EditTextShortDescriptionCar);

        transition();

        Intent returnIntent = getIntent();
        fotoString = returnIntent.getStringExtra("image");
        byte[] decodedString = android.util.Base64.decode(fotoString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        photo.setImageBitmap(decodedByte);
    }
    private void transition() {
        LinearLayout buttonPublish = findViewById(R.id.ButtonPublish);
        buttonPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isEverythingOK = true;
                String nameCar = EditTextNameCar.getText().toString();
                String descriptionCar = EditTextDescriptionCar.getText().toString();
                String priceCar = EditTextPriceCar.getText().toString();
                String numberUser = EditTextNumber.getText().toString();
                String miniDescription = EditTextShortDescriptionCar.getText().toString();

                if (nameCar.isEmpty()){
                    isEverythingOK = false;
                    EditTextNameCar.setError("это поле должно быть заполненно");
                } else if (descriptionCar.isEmpty()) {
                    isEverythingOK = false;
                    EditTextDescriptionCar.setError("Это поле осталось пустым");
                } else if (priceCar.isEmpty()) {
                    isEverythingOK = false;
                    EditTextPriceCar.setError("Это поле осталось пустым");
                } else if (numberUser.isEmpty()) {
                    isEverythingOK = false;
                    EditTextNumber.setError("Это поле осталось пустым");
                } else if (miniDescription.isEmpty()) {
                    isEverythingOK = false;
                    EditTextShortDescriptionCar.setError("Это поле осталось пустым");
                }

                if (isEverythingOK) {
                    String key = publications.child(auth.getKey()).push().getKey();
                    CarClass car = new CarClass(key, auth.getKey(), nameCar,descriptionCar, priceCar,numberUser,fotoString,miniDescription);
                    publications.child(auth.getKey()).child(key).setValue(car).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            setResult(RESULT_OK);
                            finish();
                        }
                    });
                }
            }
        });
        }
    }
