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

public class AddMovieActivity extends BaseActivity {

    private CrudService crudService;
    private Button addBtn;
    private EditText movieIdTxt;
    private EditText movieNameTxt;
    private EditText seriesTxt;
    private EditText imageUrlTxt;
    private EditText descriptionTxt;
    private Movie movie;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setupViews();
        setupApiService();
        Intent intent =getIntent();
        if(intent.hasExtra(Constants.KEY_MOVIES)) {
            getSupportActionBar().setTitle("Edit Movie");
            movie = (Movie) intent.getSerializableExtra(Constants.KEY_MOVIES);
            showData();
            handleEdit();
            showEditBtn();
        } else {
            getSupportActionBar().setTitle("Add Movie");
            showAddBtn();
            handleAdd();
        }
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
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

    private void showData() {
        movieIdTxt.setText(movie.movieId);
        movieNameTxt.setText(movie.name);
        seriesTxt.setText(movie.movieSeriesId);
        imageUrlTxt.setText(movie.imageUrl);
        descriptionTxt.setText(movie.description);
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
        editBtn = findViewById(R.id.edit_btn);
        movieIdTxt = findViewById(R.id.movie_id_txt);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        seriesTxt = findViewById(R.id.series_txt);
        imageUrlTxt = findViewById(R.id.imageUrl_txt);
        descriptionTxt = findViewById(R.id.description_txt);
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String movieId = movieIdTxt.getText().toString();
            String movieName = movieNameTxt.getText().toString();
            String series = seriesTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            String description = descriptionTxt.getText().toString();
            Movie movie =  createMovies(movieId, movieName, series, imageUrl, description);
            saveMessage(movie);
        });
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private Movie createMovies(String movieId, String movieName, String series, String imageUrl, String description) {
        Movie movie = new Movie();
        movie.movieId = movieId;
        movie.name = movieName;
        movie.movieSeriesId = series;
        movie.imageUrl = imageUrl;
        movie.description = description;
        return movie;
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