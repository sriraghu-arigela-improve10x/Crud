package com.improve10x.crud.movies;

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
import com.improve10x.crud.databinding.ActivityAddMovieBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {
    protected ActivityAddMovieBinding binding;
    protected CrudService crudService;
    protected Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupApiService();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    protected Movie createMovies(String movieId, String movieName, String series, String imageUrl, String description) {
        Movie movie = new Movie();
        movie.movieId = movieId;
        movie.name = movieName;
        movie.movieSeriesId = series;
        movie.imageUrl = imageUrl;
        movie.description = description;
        return movie;
    }
}