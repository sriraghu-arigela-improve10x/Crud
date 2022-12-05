package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.improve10x.crud.messages.MessagesActivity;
import com.improve10x.crud.movies.MoviesActivity;
import com.improve10x.crud.series.SeriesActivity;
import com.improve10x.crud.templates.TemplatesActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        handleMessageBtn();
        handleTemplatesBtn();
        handleSeriesBtn();
        handleMoviesBtn();
    }

    public void handleMessageBtn() {
        ImageButton imageButton = findViewById(R.id.image_btn);
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MessagesActivity.class);
            startActivity(intent);
        });
    }

    public void handleTemplatesBtn() {
        ImageButton templateImageBtn = findViewById(R.id.template_img_btn);
        templateImageBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, TemplatesActivity.class);
            startActivity(intent);
        });
    }

    public void handleSeriesBtn() {
       ImageButton seriesImageBtn = findViewById(R.id.series_img_btn);
       seriesImageBtn.setOnClickListener(view -> {
           Intent intent = new Intent(this, SeriesActivity.class);
           startActivity(intent);
       });
    }

    public void handleMoviesBtn() {
       ImageButton moviesImageBtn = findViewById(R.id.movies_img_btn);
       moviesImageBtn.setOnClickListener(view -> {
           Intent intent = new Intent(this, MoviesActivity.class);
           startActivity(intent);
       });
    }
}