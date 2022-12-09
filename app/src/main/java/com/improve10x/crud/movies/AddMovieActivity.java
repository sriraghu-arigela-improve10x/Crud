package com.improve10x.crud.movies;

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

public class AddMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        getSupportActionBar().setTitle("Add Movie");
        handleAdd();
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

    private void createMovies(String movieId, String movieName, String series, String imageUrl, String description) {
        Movie movie = new Movie();
        movie.movieId = movieId;
        movie.name = movieName;
        movie.movieSeriesId = series;
        movie.imageUrl = imageUrl;
        movie.description = description;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Movie> call = crudService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Toast.makeText(AddMovieActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(AddMovieActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}