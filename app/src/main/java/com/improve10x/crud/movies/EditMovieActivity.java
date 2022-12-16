package com.improve10x.crud.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMovieActivity extends BaseAddEditMovieActivity{

    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        Intent intent =getIntent();
        if(intent.hasExtra(Constants.KEY_MOVIES)) {
            getSupportActionBar().setTitle("Edit Movie");
            movie = (Movie) intent.getSerializableExtra(Constants.KEY_MOVIES);
            showData();
            handleEdit();
            showEditBtn();
        }
    }

    private void setupViews() {
        editBtn = findViewById(R.id.edit_btn);
    }

    private void showData() {
        movieIdTxt.setText(movie.movieId);
        movieNameTxt.setText(movie.name);
        seriesTxt.setText(movie.movieSeriesId);
        imageUrlTxt.setText(movie.imageUrl);
        descriptionTxt.setText(movie.description);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String movieId = movieIdTxt.getText().toString();
            String movieName = movieNameTxt.getText().toString();
            String series = seriesTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            String description = descriptionTxt.getText().toString();
            Movie updatedMovie =  createMovies(movieId, movieName, series, imageUrl, description);
            updateMovie(movie.id, updatedMovie);
        });
    }

    private void updateMovie(String movieId, Movie updatedMovie) {
        Call<Void> call = crudService.updatedMovie(movieId, updatedMovie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Success");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("failed");
            }
        });
    }

    private void showEditBtn() {
        editBtn.setVisibility(View.VISIBLE);
    }
}
