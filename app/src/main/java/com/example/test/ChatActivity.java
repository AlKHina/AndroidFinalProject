package com.example.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    ImageView ava_message, sendingImageView,go_back;
    TextView nick_message, time_message;
    ListView chatListView;
    EditText chatEditText;
    private DatabaseReference db;
    DatabaseReference messagesReference;
    ArrayList<MessageClass> chat;
    MessageAdapter adapter;
    AuthClass auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        auth = new AuthClass(getApplicationContext());

        chat = new ArrayList<>();
        adapter = new MessageAdapter(this, chat);

        db = FirebaseDatabase.getInstance().getReference();
        messagesReference = db.child("messages");

        defineViews();
        addingFunctional();
        readingFunctional();
        deleteAlertDialog();
        editAlertDialog();
        backListner();
    }

    private void editAlertDialog() {
        chatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void addingFunctional() {
        sendingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!chatEditText.getText().toString().isEmpty()) {
                    String massage = chatEditText.getText().toString();
                    String key = messagesReference.push().getKey();
                    MessageClass message = new MessageClass(massage,key, System.currentTimeMillis(), auth.getKey());
                    messagesReference.child(key).setValue(message);
                    chatEditText.setText("");
                }
            }
        });
    }

    private void readingFunctional() {

        chatListView.setAdapter(adapter);
        messagesReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                chat.clear();
                for (DataSnapshot msgSnapshot : snapshot.getChildren()) {
                    MessageClass message = msgSnapshot.getValue(MessageClass.class);
                    chat.add(message);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void deleteAlertDialog() {
        chatListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(ChatActivity.this);
                MessageClass pickedMessage = chat.get(position);
                String text = "сообщение:" + pickedMessage.getMessage();
                deleteDialog.setTitle("вы действительно хотите удалить сообщение?");
                deleteDialog.setMessage(text);
                deleteDialog.setPositiveButton("удалить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        messagesReference.child(pickedMessage.getKey()).removeValue();
                    }
                });
                deleteDialog.setNegativeButton("оставить", null);
                return true;
            }
        });
    }

        private void backListner () {

            findViewById(R.id.go_back).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(ChatActivity.this, ChatListActivity.class);
                    startActivity(intent);
                }
            });
        }

    private void defineViews() {

        ava_message = findViewById(R.id.ava_message);
        sendingImageView = findViewById(R.id.sendingImageView);
        nick_message = findViewById(R.id.nick_message);
        time_message = findViewById(R.id.time_message);
        chatEditText = findViewById(R.id.chatEditText);
        chatListView = findViewById(R.id.chatListView);
    }
}