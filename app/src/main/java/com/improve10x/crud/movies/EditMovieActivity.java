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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =getIntent();
        if(intent.hasExtra(Constants.KEY_MOVIES)) {
            getSupportActionBar().setTitle("Edit Movie");
            movie = (Movie) intent.getSerializableExtra(Constants.KEY_MOVIES);
            showData();
            handleEdit();
            showEditBtn();
        }
    }

    private void showData() {
        binding.movieIdTxt.setText(movie.movieId);
        binding.movieNameTxt.setText(movie.name);
        binding.seriesTxt.setText(movie.movieSeriesId);
        binding.imageUrlTxt.setText(movie.imageUrl);
        binding.descriptionTxt.setText(movie.description);
    }

    private void handleEdit() {
        binding.editBtn.setOnClickListener(view -> {
            String movieId = binding.movieIdTxt.getText().toString();
            String movieName = binding.movieNameTxt.getText().toString();
            String series = binding.seriesTxt.getText().toString();
            String imageUrl = binding.imageUrlTxt.getText().toString();
            String description = binding.descriptionTxt.getText().toString();
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
        binding.editBtn.setVisibility(View.VISIBLE);
    }
}
