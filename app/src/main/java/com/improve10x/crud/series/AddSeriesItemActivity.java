package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        getSupportActionBar().setTitle("Add Series");
        handleAdd();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText seriesIdTxt = findViewById(R.id.seriesid_txt);
            String seriesId = seriesIdTxt.getText().toString();
            EditText seriesNameTxt = findViewById(R.id.series_name_txt);
            String seriesName = seriesNameTxt.getText().toString();
            EditText imageUrlTxt = findViewById(R.id.image_url_txt);
            String imageUrl = imageUrlTxt.getText().toString();
            createSeriesItems(seriesId, seriesName, imageUrl);
        });
    }

    private void createSeriesItems(String seriesId, String seriesName, String imageUrl) {
        SeriesItem series = new SeriesItem();
        series.seriesId = seriesId;
        series.title = seriesName;
        series.imageUrl = imageUrl;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<SeriesItem> call = crudService.createSeries(series);
        call.enqueue(new Callback<SeriesItem>() {
            @Override
            public void onResponse(Call<SeriesItem> call, Response<SeriesItem> response) {
                Toast.makeText(AddSeriesItemActivity.this, "Successfully Completed", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<SeriesItem> call, Throwable t) {
                Toast.makeText(AddSeriesItemActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}