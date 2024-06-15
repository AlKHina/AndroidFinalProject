package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserAdapter extends ArrayAdapter<UserClass> {
    Context context;
    ArrayList<UserClass> users;
    View v;
    private AuthClass auth;

    public UserAdapter(@NonNull Context context, ArrayList<UserClass> users) {
        super(context, R.layout.alert_dialog_nick,users);
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserClass massages = users.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.alert_dialog_nick, null, false);

        TextView newNick = v.findViewById(R.id.newNick);
        TextView confirmNewNick = v.findViewById(R.id.confirmNewNick);
        boolean confirmNick = true;

        if (newNick.equals(confirmNick)) {
            Toast.makeText(UserAdapter.this, "Пароль успешно изменен", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(UserAdapter.this, "Новый пароль и его подтверждение не совпадают", Toast.LENGTH_SHORT).show();
        }
        if (confirmNick) {
            Query query = FirebaseDatabase.getInstance().getReference().child("accounts").orderByChild("nick").equalTo(pass);
            query.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChildren()) {
                        UserClass account = new UserClass();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            account = snapshot1.getValue(UserClass.class);
                        }
                        if (newNick.equals(account.getNick())) {
                            auth.setUser(account);
                            auth.setKey(account.getKey());

                            Intent intent = new Intent(UserAdapter.this, UserActivity.class);
                            startActivity(intent);
                            newNick.setText(users.getNick());
                            return v;
                        }
                    }
                }
            });
        }
    });
}

