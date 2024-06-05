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

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<OpisanieClass> cars;
    MainAdapter adapter;
    GridView gridView;
    String[] numberWord = {"One","Two","tree"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gv);

        cars = new ArrayList<>();
        adapter = new MainAdapter(this, cars);
        gridView.setAdapter(adapter);
        loadCars();
    }
    private void loadCars(){
        cars.add(new OpisanieClass(getResources().getDrawable(R.drawable.toyota_camry_70),"toyota camry 70","Объем               Мощность \n" +
                "2.0 л                    178 л.с.\n" +
                "\n" +
                "Коробка            Тип двигателя\n" +
                "Вариатор          бензиновый \n" +
                "\n" +
                "Топливо            Привод\n" +
                "АИ-95                передний\n" +
                "\n" +
                "Расход               Кл автомобиля \n" +
                "5.7 л                    D ","$220000" , "+7 (777) 777-77-77", "Седан D-класса, передний и полный привод. Вариатор. Бензиновые и гибридные двигатели мощностью от 173 до 232 лошадиных сил."));
        cars.add(new OpisanieClass(getResources().getDrawable(R.drawable.toyota_camry_70),"toyota camry 70","Объем               Мощность \n" +
                "2.0 л                    178 л.с.\n" +
                "\n" +
                "Коробка            Тип двигателя\n" +
                "Вариатор          бензиновый \n" +
                "\n" +
                "Топливо            Привод\n" +
                "АИ-95                передний\n" +
                "\n" +
                "Расход               Кл автомобиля \n" +
                "5.7 л                    D ","$220000" , "+7 (777) 777-77-77", "Седан D-класса, передний и полный привод. Вариатор. Бензиновые и гибридные двигатели мощностью от 173 до 232 лошадиных сил."));
        cars.add(new OpisanieClass(getResources().getDrawable(R.drawable.toyota_camry_70),"toyota camry 70","Объем               Мощность \n" +
                "2.0 л                    178 л.с.\n" +
                "\n" +
                "Коробка            Тип двигателя\n" +
                "Вариатор          бензиновый \n" +
                "\n" +
                "Топливо            Привод\n" +
                "АИ-95                передний\n" +
                "\n" +
                "Расход               Кл автомобиля \n" +
                "5.7 л                    D ","$220000" , "+7 (777) 777-77-77", "Седан D-класса, передний и полный привод. Вариатор. Бензиновые и гибридные двигатели мощностью от 173 до 232 лошадиных сил."));
        cars.add(new OpisanieClass(getResources().getDrawable(R.drawable.toyota_camry_70),"toyota camry 70","Объем               Мощность \n" +
                "2.0 л                    178 л.с.\n" +
                "\n" +
                "Коробка            Тип двигателя\n" +
                "Вариатор          бензиновый \n" +
                "\n" +
                "Топливо            Привод\n" +
                "АИ-95                передний\n" +
                "\n" +
                "Расход               Кл автомобиля \n" +
                "5.7 л                    D ","$220000" , "+7 (777) 777-77-77", "Седан D-класса, передний и полный привод. Вариатор. Бензиновые и гибридные двигатели мощностью от 173 до 232 лошадиных сил."));
        cars.add(new OpisanieClass(getResources().getDrawable(R.drawable.toyota_camry_70),"toyota camry 70","Объем               Мощность \n" +
                "2.0 л                    178 л.с.\n" +
                "\n" +
                "Коробка            Тип двигателя\n" +
                "Вариатор          бензиновый \n" +
                "\n" +
                "Топливо            Привод\n" +
                "АИ-95                передний\n" +
                "\n" +
                "Расход               Кл автомобиля \n" +
                "5.7 л                    D ","$220000" , "+7 (777) 777-77-77", "Седан D-класса, передний и полный привод. Вариатор. Бензиновые и гибридные двигатели мощностью от 173 до 232 лошадиных сил."));
        cars.add(new OpisanieClass(getResources().getDrawable(R.drawable.toyota_camry_70),"toyota camry 70","Объем               Мощность \n" +
                "2.0 л                    178 л.с.\n" +
                "\n" +
                "Коробка            Тип двигателя\n" +
                "Вариатор          бензиновый \n" +
                "\n" +
                "Топливо            Привод\n" +
                "АИ-95                передний\n" +
                "\n" +
                "Расход               Кл автомобиля \n" +
                "5.7 л                    D ","$220000" , "+7 (777) 777-77-77", "Седан D-класса, передний и полный привод. Вариатор. Бензиновые и гибридные двигатели мощностью от 173 до 232 лошадиных сил."));
        assignAdapter();

    }
    private void assignAdapter(){
      adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,OpisanieActivity.class);
                OpisanieClass pickedAnimal = cars.get(position);
                intent.putExtra("name", pickedAnimal.getName());
                intent.putExtra("opisanie", pickedAnimal.getOpisanie());
                Bitmap bitmap= ((BitmapDrawable)pickedAnimal.getImage()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,70,stream);
                String imgString = android.util.Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP);
                intent.putExtra("foto", imgString);
                startActivity(intent);
            }
        });

    }
}