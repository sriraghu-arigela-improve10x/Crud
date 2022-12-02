package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesActivity extends AppCompatActivity {

    public ArrayList<Series> seriesList;
    public RecyclerView seriesRv;
    public SeriesAdapter seriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        getSupportActionBar().setTitle("Series");
        setData();
        seriesRv();
        addSeriesActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTask();
    }

    public void fetchTask() {
        SeriesApi seriesApi =new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<List<Series>> call = seriesService.fetchTask();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> series = response.body();
                seriesAdapter.setData(series);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {

            }
        });
    }

    public void addSeriesActivity() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddSeriesActivity.class);
            startActivity(intent);
        });
    }

    public void seriesRv() {
        seriesRv = findViewById(R.id.siries_rv);
        seriesRv.setLayoutManager(new LinearLayoutManager(this));
        seriesAdapter = new SeriesAdapter();
        seriesAdapter.setData(seriesList);
        seriesRv.setAdapter(seriesAdapter);
    }

    public void setData() {
        seriesList = new ArrayList<>();

        /*Series goast = new Series();
        goast.title = "Goast";
        goast.imageUrl = "https://wallpaperaccess.com/full/1280586.jpg";
        seriesList.add(goast);

        Series hits = new Series();
        hits.title = "1990s Hits & Plops";
        hits.imageUrl = "https://i.ytimg.com/vi/C2ueyddjD1M/maxresdefault.jpg";
        seriesList.add(hits);*/
    }
}