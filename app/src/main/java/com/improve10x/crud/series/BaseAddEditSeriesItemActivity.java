package com.improve10x.crud.series;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.databinding.ActivityAddSeriesBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditSeriesItemActivity extends BaseActivity {
    protected ActivityAddSeriesBinding binding;
    protected CrudService crudService;
    protected SeriesItem seriesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddSeriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupApiService();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    protected SeriesItem createSeriesItems(String seriesId, String seriesName, String imageUrl) {
        SeriesItem series = new SeriesItem();
        series.seriesId = seriesId;
        series.title = seriesName;
        series.imageUrl = imageUrl;
        return series;
    }
}