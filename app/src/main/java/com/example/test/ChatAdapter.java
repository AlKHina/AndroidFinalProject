package com.example.test;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<ChatClass> {
    Context context;
    ArrayList<ChatClass> chat;
    View v;

    public ChatAdapter(@NonNull Context context, ArrayList<ChatClass> chats) {
        super(context, R.layout.item_message,chats);
        this.context = context;
        this.chat = chats;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChatClass chat = chat.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.item_message,null,false);

        TextView message = v.findViewById(R.id.message);
        TextView time = v.findViewById(R.id.time);

        message.setText(message.getMessage());
        time.setText(message.getTime());
        return v;
    }
}
