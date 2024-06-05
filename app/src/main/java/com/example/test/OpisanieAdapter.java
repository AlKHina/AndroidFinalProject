package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.OpisanieClass;
import com.example.test.R;

import java.util.ArrayList;

public class OpisanieAdapter extends ArrayAdapter<OpisanieClass> {

    Context context;
    ArrayList<OpisanieClass> cars;
    View v;

    public OpisanieAdapter(@NonNull Context context, ArrayList<OpisanieClass> cars) {
        super(context, R.layout.item_public, cars);
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OpisanieClass cars = this.cars.get(position);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        v = inflater.inflate(R.layout.item_public, null, false);
        ImageView imageView = v.findViewById(R.id.ivGallery);
        TextView textView = v.findViewById(R.id.title);
        imageView.setImageDrawable(cars.getImage());
        textView.setText(cars.getName());
        return v;
    }
}
// OpisanieClass cars = this.cars.get(position);
//        LayoutInflater inflater = LayoutInflater.from(this.context);
//        v = inflater.inflate(R.layout.item_public, null, false);
//        ImageView imageView = v.findViewById(R.id.ivGallery);
//        TextView textView = v.findViewById(R.id.title);
//        imageView.setImageDrawable(cars.getImage());
//        textView.setText(cars.getName());