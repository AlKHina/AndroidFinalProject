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

import java.util.ArrayList;

public class DescriptionAdapter extends ArrayAdapter<DescriptionClass> {

    Context context;
    ArrayList<DescriptionClass> cars;
    View v;

    public DescriptionAdapter(@NonNull Context context, ArrayList<DescriptionClass> cars) {
        super(context, R.layout.item_public, cars);
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DescriptionClass cars = this.cars.get(position);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        v = inflater.inflate(R.layout.item_public, null, false);
        ImageView imageView = v.findViewById(R.id.ivGallery);
        TextView textView = v.findViewById(R.id.title);
        imageView.setImageDrawable(cars.getImage());
        textView.setText(cars.getName());
        return v;
    }
}