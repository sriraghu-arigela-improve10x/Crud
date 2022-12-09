package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends AppCompatActivity {
    private ArrayList<SeriesItem> seriesList;
    private RecyclerView seriesItemsRv;
    private SeriesItemsAdapter seriesItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        Log.i("SeriesItemsActivity", "onCreate Call");
        getSupportActionBar().setTitle("Series");
        setupData();
        setupSeriesItemsRv();
        handleAdd();
    }

    private void deleteSeries(SeriesItem series) {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.deleteSeries(series.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SeriesItemsActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                fetchSeries();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SeriesItemsActivity.this, "Fail to Delete Message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SeriesItemsActivity", "onResume Call");
        fetchSeries();
    }

    private void fetchSeries() {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<List<SeriesItem>> call = crudService.fetchSeries();
        call.enqueue(new Callback<List<SeriesItem>>() {
            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                List<SeriesItem> series = response.body();
                seriesItemsAdapter.setData(series);
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {

            }
        });
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddSeriesItemActivity.class);
            startActivity(intent);
        });
    }

    private void setupSeriesItemsRv() {
        seriesItemsRv = findViewById(R.id.siries_rv);
        seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsAdapter.setData(seriesList);
        seriesItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(SeriesItem series) {
                Toast.makeText(SeriesItemsActivity.this, "On Item Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(SeriesItem series) {
                Toast.makeText(SeriesItemsActivity.this, "On Item Delete", Toast.LENGTH_SHORT).show();
                deleteSeries(series);
            }

            @Override
            public void onItemEdit(SeriesItem series) {
                Toast.makeText(SeriesItemsActivity.this, "On Item Edit", Toast.LENGTH_SHORT).show();
            }
        });
        seriesItemsRv.setAdapter(seriesItemsAdapter);
    }

    private void setupData() {
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