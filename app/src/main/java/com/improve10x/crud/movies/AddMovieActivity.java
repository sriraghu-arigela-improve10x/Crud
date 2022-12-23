package com.improve10x.crud.movies;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends BaseAddEditMovieActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Movie");
        showAddBtn();
        handleAdd();
    }

    private void showAddBtn() {
        binding.addBtn.setVisibility(View.VISIBLE);
    }

    private void handleAdd() {
        binding.addBtn.setOnClickListener(view -> {
            String movieId = binding.movieIdTxt.getText().toString();
            String movieName = binding.movieNameTxt.getText().toString();
            String series = binding.seriesTxt.getText().toString();
            String imageUrl = binding.imageUrlTxt.getText().toString();
            String description = binding.descriptionTxt.getText().toString();
            Movie movie =  createMovies(movieId, movieName, series, imageUrl, description);
            saveMessage(movie);
        });
    }

    private void saveMessage(Movie movie) {
        Call<Movie> call = crudService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                showToast("SuccessFully");
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                showToast("Try Again");
            }
        });
    }
}
