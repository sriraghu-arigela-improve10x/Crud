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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseActivity {

    private CrudService crudService;
    private Button addBtn;
    private EditText seriesIdTxt;
    private EditText seriesNameTxt;
    private EditText seriesImageUrlTxt;
    private SeriesItem seriesItem;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        Intent intent = getIntent();
        setupViews();
        setupApiService();
        if(intent.hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit SeriesItem");
            seriesItem = (SeriesItem) intent.getSerializableExtra(Constants.KEY_SERIES);
            showData();
            handleEdit();
            showEditBtn();
        } else {
            getSupportActionBar().setTitle("Add SeriesItem");
            handleAdd();
            showAddBtn();
        }
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String seriesId = seriesIdTxt.getText().toString();
            String seriesName = seriesNameTxt.getText().toString();
            String imageUrl = seriesImageUrlTxt.getText().toString();
            SeriesItem updatedSeriesItem = createSeriesItems(seriesId, seriesName, imageUrl);
            updateSeriesItem(seriesItem.id, updatedSeriesItem);
        });
    }

    private void updateSeriesItem(String id, SeriesItem updatedSeriesItem) {
        Call<Void> call = crudService.updateSeries(id, updatedSeriesItem);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed");
            }
        });
    }

    private void showData() {
        seriesIdTxt.setText(seriesItem.seriesId);
        seriesNameTxt.setText(seriesItem.title);
        seriesImageUrlTxt.setText(seriesItem.imageUrl);
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
        editBtn = findViewById(R.id.edit_btn);
        seriesIdTxt = findViewById(R.id.seriesid_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        seriesImageUrlTxt = findViewById(R.id.image_url_txt);
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String seriesId = seriesIdTxt.getText().toString();
            String seriesName = seriesNameTxt.getText().toString();
            String imageUrl = seriesImageUrlTxt.getText().toString();
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