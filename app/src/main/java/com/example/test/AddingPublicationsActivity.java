package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddingPublicationsActivity extends AppCompatActivity {

    private LinearLayout gallery, camera;
    private static int PICK_IMAGE = 40;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_publications);

        publicationsGallery();
        publicationsСamera();
        homeListner();
        searchListner();
        likeListner();
        userListner();
        messageListner();
    }
    private void publicationsGallery() {

        gallery = findViewById(R.id.buttonDialogGallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Прикрепление картинки"), PICK_IMAGE);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    Intent intent = new Intent(AddingPublicationsActivity.this, ToPublishActivity.class);
                    String imgString = android.util.Base64.encodeToString(bytes, Base64.NO_WRAP);
                    intent.putExtra("image", imgString);
                    startActivity(intent);
                    finish();
                    image = Base64.encodeToString(bytes, Base64.DEFAULT);
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
            Intent intent = new Intent(AddingPublicationsActivity.this, ToPublishActivity.class);
            String imgString = android.util.Base64.encodeToString(bytes, Base64.NO_WRAP);
            intent.putExtra("image", imgString);
            startActivity(intent);
            finish();
            image = Base64.encodeToString(bytes, Base64.DEFAULT);
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
    private void publicationsСamera() {

        camera = findViewById(R.id.buttonDialogCamera);
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }
    private void homeListner(){
        findViewById(R.id.home_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddingPublicationsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void searchListner() {
        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddingPublicationsActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
    private void likeListner() {
        findViewById(R.id.liked_it_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddingPublicationsActivity.this, LikeActivity.class);
                startActivity(intent);
            }
        });
    }
    private void userListner() {
        findViewById(R.id.User_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddingPublicationsActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
    private void messageListner(){
        findViewById(R.id.message_button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddingPublicationsActivity.this, ChatListActivity.class);
                startActivity(intent);
            }
        });
    }
}



