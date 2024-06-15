package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<CarClass> cars;
    MainAdapter adapter;
    GridView gridView;
    String[] numberWord = {"One", "Two", "tree"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gv);

        cars = new ArrayList<>();
        adapter = new MainAdapter(this, cars);
        gridView.setAdapter(adapter);
        loadCars();
        menuListner();
        searchListner();
        likeListner();
        userListner();
        messageListner();
    }

    private void loadCars() {
        FirebaseDatabase.getInstance().getReference().child("publications").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userPublichions : snapshot.getChildren()) {
                    for (DataSnapshot publichionssnapshot : userPublichions.getChildren()) {
                        CarClass publicahins = publichionssnapshot.getValue(CarClass.class);
                        cars.add(publicahins);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        assignAdapter();

    }

    private void assignAdapter() {
        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, OpisanieActivity.class);
                CarClass pickedCar = cars.get(position);
                intent.putExtra("name", pickedCar.getNameCar());
                intent.putExtra("opisanie", pickedCar.getDescriptionCar());
                intent.putExtra("foto", pickedCar.getFoto());
                startActivity(intent);
            }
        });

    }

    private void menuListner() {
        findViewById(R.id.To_add_an_advert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddingPublicationsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void searchListner() {
        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
    private void likeListner() {
        findViewById(R.id.liked_it_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LikeActivity.class);
                startActivity(intent);
            }
        });
    }
    private void userListner() {
        findViewById(R.id.User_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
    private void messageListner(){
        findViewById(R.id.message_button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChatListActivity.class);
                startActivity(intent);
            }
        });
    }
}