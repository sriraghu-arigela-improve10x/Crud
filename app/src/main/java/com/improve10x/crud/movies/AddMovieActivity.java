package com.improve10x.crud.movies;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        getSupportActionBar().setTitle("Add Movie");
        setupViews();
        handleAdd();
        setupApiService();
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
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