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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected CrudService crudService;
    protected EditText movieIdTxt;
    protected EditText movieNameTxt;
    protected EditText seriesTxt;
    protected EditText imageUrlTxt;
    protected EditText descriptionTxt;
    protected Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setupViews();
        setupApiService();
    }

    private void setupViews() {
        movieIdTxt = findViewById(R.id.movie_id_txt);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        seriesTxt = findViewById(R.id.series_txt);
        imageUrlTxt = findViewById(R.id.imageUrl_txt);
        descriptionTxt = findViewById(R.id.description_txt);
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