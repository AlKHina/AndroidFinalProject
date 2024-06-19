package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<CarClass> cars;
    MainAdapter adapter;
    GridView gridView;
    AuthClass auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = new AuthClass(getApplicationContext());
        if (auth.getUser() == null) {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        gridView = findViewById(R.id.gv);

        cars = new ArrayList<>();
        adapter = new MainAdapter(this, cars);
        gridView.setAdapter(adapter);
        loadCars();
        searchListner();
        publicationsListner();
        likeListner();
        userListner();
        messageListner();
    }

    private void loadCars() {
        FirebaseDatabase.getInstance().getReference().child("publications").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cars.clear();
                for (DataSnapshot userPublication : snapshot.getChildren()) {
                    for (DataSnapshot publicationsSnapshot : userPublication.getChildren()) {
                        CarClass Publications = publicationsSnapshot.getValue(CarClass.class);
                        cars.add(Publications);
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
                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                CarClass pickedCar = cars.get(position);
                intent.putExtra("nameCar", pickedCar.getNameCar());
                intent.putExtra("descriptionCar", pickedCar.getDescriptionCar());
                intent.putExtra("photo", pickedCar.getPhoto());
                intent.putExtra("key", pickedCar.getKey());
                intent.putExtra("phone", pickedCar.getNumberUser());
                intent.putExtra("price", pickedCar.getPriceCar());
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
    private void publicationsListner() {
        findViewById(R.id.To_add_an_advert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddingPublicationsActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            loadCars();
        }
    }
}