package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;

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

    public void deleteSeries(Series series) {
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Void> call = seriesService.deleteSeries(series.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SeriesActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                fetchSeries();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SeriesActivity.this, "Fail to Delete Message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchSeries();
    }

    public void fetchSeries() {
        SeriesApi seriesApi =new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<List<Series>> call = seriesService.fetchSeries();
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
        seriesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Series series) {
                Toast.makeText(SeriesActivity.this, "On Item Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Series series) {
                Toast.makeText(SeriesActivity.this, "On Item Delete", Toast.LENGTH_SHORT).show();
                deleteSeries(series);
            }

            @Override
            public void onItemEdit(Series series) {
                Toast.makeText(SeriesActivity.this, "On Item Edit", Toast.LENGTH_SHORT).show();
            }
        });
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