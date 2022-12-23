package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.improve10x.crud.databinding.ActivityDashboardBinding;
import com.improve10x.crud.messages.MessagesActivity;
import com.improve10x.crud.movies.MoviesActivity;
import com.improve10x.crud.quotes.QuotesActivity;
import com.improve10x.crud.series.SeriesItemsActivity;
import com.improve10x.crud.templates.TemplatesActivity;

public class DashboardActivity extends AppCompatActivity {
    private ActivityDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Dashboard");
        handleMessageBtn();
        handleTemplatesBtn();
        handleSeriesBtn();
        handleMoviesBtn();
        handleQuotesBtn();
    }

    public void handleMessageBtn() {
        binding.imageBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MessagesActivity.class);
            startActivity(intent);
        });
    }

    public void handleTemplatesBtn() {
        binding.templateImgBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, TemplatesActivity.class);
            startActivity(intent);
        });
    }

    public void handleSeriesBtn() {
       binding.seriesImgBtn.setOnClickListener(view -> {
           Intent intent = new Intent(this, SeriesItemsActivity.class);
           startActivity(intent);
       });
    }

    public void handleMoviesBtn() {
       binding.moviesImgBtn.setOnClickListener(view -> {
           Intent intent = new Intent(this, MoviesActivity.class);
           startActivity(intent);
       });
    }

    public void handleQuotesBtn() {
        binding.quoteImgBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, QuotesActivity.class);
            startActivity(intent);
        });
    }
}