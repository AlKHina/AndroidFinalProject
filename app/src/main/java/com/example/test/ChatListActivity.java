package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    ListView lv;
    ImageView arrow;
    ArrayList<ChatClass> chat;
    ChatAdapter adapter;
    private DatabaseReference db;
    DatabaseReference messagesReference;
    AuthClass auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        lv = findViewById(R.id.chatListView);
        arrow = findViewById(R.id.arrow);

        chat = new ArrayList<>();
        adapter = new ChatAdapter(this, chat);
        lv.setAdapter(adapter);

        db = FirebaseDatabase.getInstance().getReference();
        messagesReference = db.child("chat");

        chatlist();
        backListner();

    }

    private void chatlist() {

            adapter = new ChatAdapter(this,chat);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(ChatListActivity.this,ChatActivity.class);
                    ChatClass pickedchat= chat.get(position);
                    intent.putExtra("chatKey",pickedchat.getChatKey());
                    startActivity(intent);
                }
            });
    }
    private void backListner () {

        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChatListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}