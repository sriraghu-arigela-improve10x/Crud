package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        getSupportActionBar().setTitle("Add Series");
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText seriesIdTxt = findViewById(R.id.seriesid_txt);
            String seriesId = seriesIdTxt.getText().toString();
            EditText seriesNameTxt = findViewById(R.id.series_name_txt);
            String seriesName = seriesNameTxt.getText().toString();
            EditText imageUrlTxt = findViewById(R.id.image_url_txt);
            String imageUrl = imageUrlTxt.getText().toString();
            createSeries(seriesId, seriesName, imageUrl);
        });
    }

    public void createSeries(String seriesId, String seriesName, String imageUrl) {
        Series series = new Series();
        series.id = seriesId;
        series.title = seriesName;
        series.imageUrl = imageUrl;

        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Series> call = seriesService.createSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                Toast.makeText(AddSeriesActivity.this, "Successfully Completed", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                Toast.makeText(AddSeriesActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}