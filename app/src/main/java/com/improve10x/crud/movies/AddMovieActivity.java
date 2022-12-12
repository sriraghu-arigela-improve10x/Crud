package com.improve10x.crud.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends BaseActivity {

    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        getSupportActionBar().setTitle("Add Movie");
        handleAdd();
        setupApiService();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText movieIdTxt = findViewById(R.id.movie_id_txt);
            String movieId = movieIdTxt.getText().toString();
            EditText movieNameTxt = findViewById(R.id.movie_name_txt);
            String movieName = movieNameTxt.getText().toString();
            EditText seriesTxt = findViewById(R.id.series_txt);
            String series = seriesTxt.getText().toString();
            EditText imageUrlTxt = findViewById(R.id.imageUrl_txt);
            String imageUrl = imageUrlTxt.getText().toString();
            EditText descriptionTxt = findViewById(R.id.description_txt);
            String description = descriptionTxt.getText().toString();
            createMovies(movieId, movieName, series, imageUrl, description);
        });
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void createMovies(String movieId, String movieName, String series, String imageUrl, String description) {
        Movie movie = new Movie();
        movie.movieId = movieId;
        movie.name = movieName;
        movie.movieSeriesId = series;
        movie.imageUrl = imageUrl;
        movie.description = description;

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