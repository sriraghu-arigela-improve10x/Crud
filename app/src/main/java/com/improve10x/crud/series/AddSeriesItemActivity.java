package com.improve10x.crud.series;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseActivity {

    private CrudService crudService;
    private Button addBtn;
    private EditText seriesIdTxt;
    private EditText seriesNameTxt;
    private EditText imageUrlTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        getSupportActionBar().setTitle("Add Series");
        setupViews();
        handleAdd();
        setupApiService();
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
        seriesIdTxt = findViewById(R.id.seriesid_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String seriesId = seriesIdTxt.getText().toString();
            String seriesName = seriesNameTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            SeriesItem seriesItem =  createSeriesItems(seriesId, seriesName, imageUrl);
            saveMessage(seriesItem);
        });
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private SeriesItem createSeriesItems(String seriesId, String seriesName, String imageUrl) {
        SeriesItem series = new SeriesItem();
        series.seriesId = seriesId;
        series.title = seriesName;
        series.imageUrl = imageUrl;
        return series;
    }

    private void saveMessage(SeriesItem series) {
        Call<SeriesItem> call = crudService.createSeries(series);
        call.enqueue(new Callback<SeriesItem>() {
            @Override
            public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                showToast("Successfully");
                finish();
            }

            @Override
            public void onFailure(Call<SeriesItem> call, Throwable t) {
                showToast("Try Again");
            }
        });
    }
}