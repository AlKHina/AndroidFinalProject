package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    LinearLayout arrow_email, arrow_password, arrow_number, arrow_publications;
    TextView change_nick;
    ImageView change_ava;
    AlertDialog dialog;
    AuthClass auth;
    private static int PICK_IMAGE = 40;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        auth = new AuthClass(getApplicationContext());

        change_ava = findViewById(R.id.change_ava);
        arrow_email = findViewById(R.id.arrow_email);
        arrow_password = findViewById(R.id.arrow_password);
        arrow_number = findViewById(R.id.arrow_number);
        arrow_publications = findViewById(R.id.arrow_publications);
        change_nick = findViewById(R.id.change_nick);

        if (auth.getAva()!=null){
            if (!auth.getAva().isEmpty()){
                byte[] decodedString = android.util.Base64.decode(auth.getAva(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                change_ava.setImageBitmap(decodedByte);
            }
        }

        change_nick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.alert_dialog_nick, null);
                EditText nameEdetText = alertView.findViewById(R.id.newNick);
                Button okButton = alertView.findViewById(R.id.changeNickOK);
                String curentName = auth.getUsername();
                nameEdetText.setText(curentName);
                Button cancellationButton = alertView.findViewById(R.id.changeNickCancellation);

                cancellationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (nameEdetText.getText().toString().isEmpty()) {
                            nameEdetText.setError("введите имя");
                        } else {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("accounts")
                                    .child(auth.getKey())
                                    .child("nick")
                                    .setValue(nameEdetText.getText().toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            Toast.makeText(UserActivity.this, " успешно изменен", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                });

                builder.setView(alertView);
                dialog = builder.create();
                dialog.show();

            }
        });
        arrow_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.alert_dialog_email, null);
                EditText emailEdetText = alertView.findViewById(R.id.newEmail);
                Button okButton = alertView.findViewById(R.id.changeEmailOK);
                String curentEmail = auth.getUser().getEmail();
                emailEdetText.setText(curentEmail);
                Button cancellationButton = alertView.findViewById(R.id.changeEmailCancellation);

                cancellationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (emailEdetText.getText().toString().isEmpty()) {
                            emailEdetText.setError("Введите email");
                        } else {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("accounts")
                                    .child(auth.getKey())
                                    .child("email")
                                    .setValue(emailEdetText.getText().toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            Toast.makeText(UserActivity.this, "email успешно изменен", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                });

                builder.setView(alertView);
                dialog = builder.create();
                dialog.show();

            }
        });
        arrow_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.alert_dialog_password, null);
                EditText newPasswordEdetText = alertView.findViewById(R.id.newPassword);
                EditText confirmPasswordEdetText = alertView.findViewById(R.id.confirmNewPassword);
                Button okButton = alertView.findViewById(R.id.changePasswordOK);
                String curentPassword = auth.getUser().getPass();
                newPasswordEdetText.setText(curentPassword);
                Button cancellationButton = alertView.findViewById(R.id.changePasswordCancellation);

                cancellationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isEverythingOK = true;

                        if (newPasswordEdetText.getText().toString().isEmpty()) {
                            newPasswordEdetText.setError("Введите пароль");
                            isEverythingOK = false;
                        } else if (!newPasswordEdetText.getText().toString().equals(confirmPasswordEdetText.getText().toString())) {
                            confirmPasswordEdetText.setError("Новый пароль и его подтверждение не совпадают");
                            isEverythingOK = false;
                            ;
                        } else {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("accounts")
                                    .child(auth.getKey())
                                    .child("pass")
                                    .setValue(newPasswordEdetText.getText().toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            Toast.makeText(UserActivity.this, "Пароль успешно изменен", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                });

                builder.setView(alertView);
                dialog = builder.create();
                dialog.show();

            }
        });
        arrow_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.alert_dialog_numder, null);
                EditText numberEdetText = alertView.findViewById(R.id.newNumber);
                Button okButton = alertView.findViewById(R.id.changeNumberOK);
                String curentNumber = auth.getUser().getNumber();
                numberEdetText.setText(curentNumber);
                Button cancellationButton = alertView.findViewById(R.id.changeNumberCancellation);
                cancellationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (numberEdetText.getText().toString().isEmpty()) {
                            numberEdetText.setError("Введите номер телефона");
                        } else {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("accounts")
                                    .child(auth.getKey())
                                    .child("number")
                                    .setValue(numberEdetText.getText().toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            Toast.makeText(UserActivity.this, "Номер телефона успешно изменен", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    }
                });

                builder.setView(alertView);
                dialog = builder.create();
                dialog.show();

            }
        });
        arrow_publications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserActivity.this, YourPublicationsActivity.class);
                startActivity(intent);
            }
        });
        change_ava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Прикрепление картинки"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                try {
                    InputStream inputStream = getApplicationContext().getContentResolver().openInputStream(data.getData());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                    bmp = getResizedBitmap(bmp, 700);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 40, stream);
                    byte[] bytes = stream.toByteArray();
                    String imgString = android.util.Base64.encodeToString(bytes, Base64.NO_WRAP);
                    FirebaseDatabase.getInstance().getReference().child("accounts").child(auth.getKey()).child("ava").setValue(imgString);
                    image = Base64.encodeToString(bytes, Base64.DEFAULT);
                    change_ava.setImageBitmap(bmp);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bmp = (Bitmap) extras.get("data");
            bmp = getResizedBitmap(bmp, 700);
            bmp.compress(Bitmap.CompressFormat.JPEG, 40, stream);
            byte[] bytes = stream.toByteArray();
            String imgString = android.util.Base64.encodeToString(bytes, Base64.NO_WRAP);
            FirebaseDatabase.getInstance().getReference().child("accounts").child(auth.getKey()).child("ava").setValue(imgString);
            image = Base64.encodeToString(bytes, Base64.DEFAULT);
            change_ava.setImageBitmap(bmp);
        }

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {

        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio < 1) {
            width = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}

