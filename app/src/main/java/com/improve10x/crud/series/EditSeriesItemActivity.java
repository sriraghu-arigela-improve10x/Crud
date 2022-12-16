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

    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit SeriesItem");
            seriesItem = (SeriesItem) intent.getSerializableExtra(Constants.KEY_SERIES);
            showData();
            handleEdit();
            showEditBtn();
        }
    }

    private void setupViews() {
        editBtn = findViewById(R.id.edit_btn);
    }

    private void showData() {
        seriesIdTxt.setText(seriesItem.seriesId);
        seriesNameTxt.setText(seriesItem.title);
        seriesImageUrlTxt.setText(seriesItem.imageUrl);
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

    private void showEditBtn() {
        editBtn.setVisibility(View.VISIBLE);
    }
}
