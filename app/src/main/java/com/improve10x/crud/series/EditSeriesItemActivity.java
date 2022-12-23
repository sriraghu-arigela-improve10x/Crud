package com.improve10x.crud.series;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit SeriesItem");
            seriesItem = (SeriesItem) intent.getSerializableExtra(Constants.KEY_SERIES);
            showData();
            handleEdit();
            showEditBtn();
        }
    }

    private void showData() {
        binding.seriesidTxt.setText(seriesItem.seriesId);
        binding.seriesNameTxt.setText(seriesItem.title);
        binding.imageUrlTxt.setText(seriesItem.imageUrl);
    }

    private void handleEdit() {
        binding.editBtn.setOnClickListener(view -> {
            String seriesId = binding.seriesidTxt.getText().toString();
            String seriesName = binding.seriesNameTxt.getText().toString();
            String imageUrl = binding.imageUrlTxt.getText().toString();
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

    private void showEditBtn() {
        binding.editBtn.setVisibility(View.VISIBLE);
    }
}
