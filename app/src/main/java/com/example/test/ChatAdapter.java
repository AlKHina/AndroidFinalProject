package com.example.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<ChatClass> {
    Context context;
    ArrayList<ChatClass> chat;
    View v;

    public ChatAdapter(@NonNull Context context, ArrayList<ChatClass> chats) {
        super(context, R.layout.item_chat, chats);
        this.context = context;
        this.chat = chats;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChatClass chats = chat.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.item_chat, null, false);

        TextView message = v.findViewById(R.id.message);
        TextView nick = v.findViewById(R.id.nick);
        ImageView ava = v.findViewById(R.id.ava_message);

        message.setText(chats.getCarName());
        nick.setText(chats.getCarName());
        byte[] decodedString = android.util.Base64.decode(chats.getAva(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ava.setImageBitmap(decodedByte);
        return v;
    }
}
