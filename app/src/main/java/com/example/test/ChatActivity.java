package com.example.test;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    ImageView ava_message, sendingImageView;
    TextView nick_message, time_message;
    ListView chatListView;
    EditText chatEditText;
    private DatabaseReference db;
    DatabaseReference messagesReference;
    ArrayList<ChatClass> chat;
    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chat = new ArrayList<>();
        adapter = new ChatAdapter(this, chat);

        db = FirebaseDatabase.getInstance().getReference();
        messagesReference = db.child("messages");

        defineViews();
        addingFunctional();
        readingFunctional();
        deleteAlertDialog();
        editAlertDialog();
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
                    ChatClass message = new ChatClass(massage, System.currentTimeMillis());
                    String key = messagesReference.push().getKey();
                    message.setKey(key);
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
                    ChatClass message = msgSnapshot.getValue(ChatClass.class);
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
                ChatClass pickedMessage = chat.get(position);
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


    private void defineViews() {
        ava_message = findViewById(R.id.ava_message);
        sendingImageView = findViewById(R.id.sendingImageView);
        nick_message = findViewById(R.id.nick_message);
        time_message = findViewById(R.id.time_message);
        chatEditText = findViewById(R.id.chatEditText);
        chatListView = findViewById(R.id.chatListView);
    }
}