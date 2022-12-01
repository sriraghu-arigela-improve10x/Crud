package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        handleMessageBtn();
        handleTemplatesBtn();
        handleSeriesBtn();
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
}