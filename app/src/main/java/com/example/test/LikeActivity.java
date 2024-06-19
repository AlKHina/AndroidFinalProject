package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LikeActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<CarClass> cars;
    MainAdapter adapter;
    private AuthClass auth;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        gridView = findViewById(R.id.gv);

        cars = new ArrayList<>();
        adapter = new MainAdapter(this, cars);
        gridView.setAdapter(adapter);

        dr = FirebaseDatabase.getInstance().getReference("favorites").child(auth.getKey());
        dr.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot publichionssnapshot : snapshot.getChildren()) {
                    CarClass publicahins = publichionssnapshot.getValue(CarClass.class);
                    cars.add(publicahins);
                }
                MainAdapter adapter = new MainAdapter(LikeActivity.this, cars);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(LikeActivity.this, DescriptionActivity.class);
                        CarClass pickedCar = cars.get(position);
                        intent.putExtra("nameCar", pickedCar.getNameCar());
                        intent.putExtra("descriptionCar", pickedCar.getDescriptionCar());
                        intent.putExtra("key",pickedCar.getKey());
                        intent.putExtra("photo", pickedCar.getPhoto());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}