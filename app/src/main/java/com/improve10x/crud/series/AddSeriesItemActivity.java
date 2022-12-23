package com.improve10x.crud.series;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add SeriesItem");
        handleAdd();
        showAddBtn();
    }

    private void handleAdd() {
        binding.addBtn.setOnClickListener(view -> {
            String seriesId = binding.seriesidTxt.getText().toString();
            String seriesName = binding.seriesNameTxt.getText().toString();
            String imageUrl = binding.imageUrlTxt.getText().toString();
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
        binding.addBtn.setVisibility(View.VISIBLE);
    }
}
