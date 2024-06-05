package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyFriend extends AppCompatActivity {
    DatabaseReference db;
    DatabaseReference bestfriend;
    Button changeFriendButton;
    TextView pokazivatDruga;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfriend);
        changeFriendButton = findViewById(R.id.ButtonChangeNameFriend);
        pokazivatDruga = findViewById(R.id.pageTextView);
        db = FirebaseDatabase.getInstance().getReference();
        bestfriend = db.child("myBestFriend");
        bestfriend.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pokazivatDruga.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        changeFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder changeFriendDialogue = new AlertDialog.Builder(MyFriend.this);
                View alertView = getLayoutInflater().inflate(R.layout.alert_dialogue,null);
                EditText nameFriend = alertView.findViewById(R.id.alertEditText);
                Button changeFriend = alertView.findViewById(R.id.alertButtonConfirm);
                changeFriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!nameFriend.getText().toString().isEmpty()) {
                            bestfriend.setValue(nameFriend.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    dialog.dismiss();
                                    pokazivatDruga.setText(nameFriend.getText().toString());
                                }
                            });
                        }
                    }
                });
                changeFriendDialogue.setView(alertView);
                dialog = changeFriendDialogue.create();
                dialog.show();
            }
        });

    }
}