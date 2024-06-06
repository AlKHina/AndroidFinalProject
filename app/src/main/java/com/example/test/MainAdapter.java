package com.example.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends ArrayAdapter<CarClass> {
    Context context;
    ArrayList<CarClass> item;

    public MainAdapter(Context context, ArrayList<CarClass> item) {
        super(context, R.layout.item_public, item);
        this.context = context;
        this.item = item;
    }
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        CarClass i = this.item.get(position);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        @SuppressLint({"ViewHolder", "InflateParams"}) View v = inflater.inflate(R.layout.item_public, null, false);

        TextView name = v.findViewById(R.id.title);
        ImageView image = v.findViewById(R.id.ivGallery);
        TextView description = v.findViewById(R.id.description);
        TextView price = v.findViewById(R.id.price);

        name.setText(i.getNameCar());
        byte[] decodedString = android.util.Base64.decode(i.getFoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        image.setImageBitmap(decodedByte);
        description.setText(i.getDescriptionCar());
        price.setText(i.getPriceCar());

        return v;
    }
}