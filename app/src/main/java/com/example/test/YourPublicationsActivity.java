package com.example.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YourPublicationsActivity extends AppCompatActivity {

    ArrayList<CarClass> cars;
    MainAdapter adapter;
    GridView gridView;
    private AuthClass auth;
    DatabaseReference publications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_publications);

        gridView = findViewById(R.id.gv);

        cars = new ArrayList<>();
        adapter = new MainAdapter(this, cars);
        gridView.setAdapter(adapter);


        publications = FirebaseDatabase.getInstance().getReference("publications").child(auth.getKey());

        publications.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                cars.clear();
                for (DataSnapshot publichionssnapshot : snapshot.getChildren()) {
                    CarClass publicahins = publichionssnapshot.getValue(CarClass.class);
                    cars.add(publicahins);
                }
                MainAdapter adapter = new MainAdapter(YourPublicationsActivity.this, cars);
                gridView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}