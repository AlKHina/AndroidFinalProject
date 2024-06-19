package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageAdapter extends ArrayAdapter<MessageClass> {

    Context context;
    ArrayList<MessageClass> massage;
    View v;

    public MessageAdapter(@NonNull Context context, ArrayList<MessageClass> massages) {
        super(context, R.layout.item_message,massages);
        this.context = context;
        this.massage = massages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MessageClass massages = massage.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.item_message,null,false);

        TextView message = v.findViewById(R.id.message);
        TextView time = v.findViewById(R.id.time);

        message.setText(massages.getMessage());
        Date currentDate = new Date(massages.getTime());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String formattedTime = timeFormat.format(currentDate);
        time.setText(formattedTime);
        return v;
    }
}
