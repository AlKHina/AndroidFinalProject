package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {

    ImageView imageViewanimalfoto,liked_publication;
    TextView textViewnazvanie;
    TextView textViewopisanie;
    TextView textViewprice;
    TextView textViewnumber;
    AuthClass auth;
    boolean favorite = false;
    DatabaseReference favoritesDB;
    String carKey;
    LinearLayout buttonCall;
    ArrayList<String> favoriteClasses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        auth = new AuthClass(getApplicationContext());
        favoritesDB = FirebaseDatabase.getInstance().getReference().child("favorites").child(auth.getKey());

        imageViewanimalfoto = findViewById(R.id.imageViewanimalfoto);
        liked_publication = findViewById(R.id.liked_publication);
        textViewnazvanie = findViewById(R.id.textViewnazvanie);
        textViewopisanie = findViewById(R.id.textViewopisanie);
        textViewprice = findViewById(R.id.textViewprice);
        textViewnumber = findViewById(R.id.textViewnumber);
        buttonCall = findViewById(R.id.ButtonСallOpisanie);

        Intent returnIntent = getIntent();
        carKey = returnIntent.getStringExtra("key");
        String fotoString = returnIntent.getStringExtra("photo");
        textViewnazvanie.setText(returnIntent.getStringExtra("nameCar"));
        textViewopisanie.setText(returnIntent.getStringExtra("descriptionCar"));
        textViewprice.setText(returnIntent.getStringExtra("price"));
        textViewnumber.setText(returnIntent.getStringExtra("phone"));
        byte[] decodedString = android.util.Base64.decode(fotoString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageViewanimalfoto.setImageBitmap(decodedByte);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + returnIntent.getStringExtra("phone")));
                startActivity(intent);
            }
        });

        FirebaseDatabase.getInstance().getReference()
                        .child("favorites")
                                .child(auth.getKey())
                .orderByChild("carKey")
                .equalTo(carKey)
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChildren()) {
                                                    liked_publication.setImageDrawable(getResources().getDrawable(R.drawable.heart, null));
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

        liked_publication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!favorite){
                    liked_publication.setImageDrawable(getResources().getDrawable(R.drawable.heart, null));
                    String key = favoritesDB.push().getKey();
                    FavoriteClass f = new FavoriteClass(carKey,key);
                    favoritesDB.child(key).setValue(f).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(DescriptionActivity.this, "Добавлено в избранные", Toast.LENGTH_SHORT).show();
                        }
                    });
                    favorite = true;
                }else{
                    liked_publication.setImageDrawable(getResources().getDrawable(R.drawable.heart111, null));
                    favoritesDB.orderByChild("Key").equalTo(carKey).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot carSnapsot:snapshot.getChildren()){
                                carSnapsot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(DescriptionActivity.this, "Удаленно с избранных", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    favorite = false;
                }
            }
        });
    }
    }
