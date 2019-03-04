package com.kerimov.adee.apigenerator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_acitvity);

        ImageView imageView = findViewById(R.id.photo_image_view);

        String url = getIntent().getStringExtra("url");

        Picasso.get().load(url).into(imageView);
    }

}
