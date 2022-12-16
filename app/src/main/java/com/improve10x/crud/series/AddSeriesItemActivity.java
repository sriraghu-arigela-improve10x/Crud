package com.improve10x.crud.series;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        getSupportActionBar().setTitle("Add SeriesItem");
        handleAdd();
        showAddBtn();
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
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

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
    }
}
