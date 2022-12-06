package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
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
        // class name change SeriesItem
        series.seriesId = seriesId;
        series.title = seriesName;
        series.imageUrl = imageUrl;

        SeriesItemsApi seriesApi = new SeriesItemsApi();
        SeriesItemsService seriesService = seriesApi.createSeriesService();
        Call<Series> call = seriesService.createSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                Toast.makeText(AddSeriesItemActivity.this, "Successfully Completed", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                Toast.makeText(AddSeriesItemActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}