package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private EditText search;
    private ImageView imageSearch;
    private GridView gv;
    private ArrayList <CarClass> cars = new ArrayList<>();
    private ArrayList <CarClass> sort = new ArrayList<>();
   MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search = findViewById(R.id.searchEditText);
        imageSearch = findViewById(R.id.imageViewSearch);
        gv = findViewById(R.id.gv);

        adapter = new MainAdapter(this, sort);
        gv.setAdapter(adapter);

        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = search.getText().toString();
                cars.clear();
                sort.clear();
                FirebaseDatabase.getInstance().getReference().child("publications").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        cars.clear();
                        sort.clear();
                        for (DataSnapshot userPublichions: snapshot.getChildren()){
                            for (DataSnapshot publichionssnapshot:userPublichions.getChildren()){
                                CarClass publicahins = publichionssnapshot.getValue(CarClass.class);
                                cars.add(publicahins);
                            }
                        }
                        for (CarClass car : cars){
                            if (car.getNameCar().contains(searchQuery.trim())){
                                sort.add(car);
                            }
                        }
                        if (sort.isEmpty()){
                            Toast.makeText(SearchActivity.this,"не нашлось машины с таким названием", Toast.LENGTH_SHORT).show();
                        }else {
                            adapter.notifyDataSetChanged();
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(SearchActivity.this,OpisanieActivity.class);
                                    CarClass pickedCar = sort.get(position);
                                    intent.putExtra("name", pickedCar.getNameCar());
                                    intent.putExtra("opisanie", pickedCar.getDescriptionCar());
                                    intent.putExtra("foto", pickedCar.getFoto());
                                    startActivity(intent);
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}