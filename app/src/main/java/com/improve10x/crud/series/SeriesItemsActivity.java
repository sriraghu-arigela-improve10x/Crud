package com.improve10x.crud.series;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends BaseActivity {

    private CrudService crudService;
    private ArrayList<SeriesItem> seriesList;
    private RecyclerView seriesItemsRv;
    private SeriesItemsAdapter seriesItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        getSupportActionBar().setTitle("Series");
        setupApiService();
        handleAdd();
        setupData();
        setupSeriesItemsRv();
        setupSeriesItemsAdapter();
        log("onCreate");
    }

    private void setupSeriesItemsAdapter() {
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsAdapter.setData(seriesList);
        seriesItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(SeriesItem series) {
                Intent intent = new Intent(SeriesItemsActivity.this, EditSeriesItemActivity.class);
                intent.putExtra(Constants.KEY_SERIES, series);
                startActivity(intent);
                //showToast("On Item Clicked");
            }

            @Override
            public void onItemDelete(SeriesItem series) {
                showToast("On Item Delete");
                deleteSeries(series);
            }

            @Override
            public void onItemEdit(SeriesItem series) {
                showToast("On Item Edit");
            }
        });
        seriesItemsRv.setAdapter(seriesItemsAdapter);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void deleteSeries(SeriesItem series) {
        Call<Void> call = crudService.deleteSeries(series.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully Deleted");
                fetchSeries();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Fail to Delete Message");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchSeries();
    }

    private void fetchSeries() {
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