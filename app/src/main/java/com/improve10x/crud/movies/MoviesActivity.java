package com.improve10x.crud.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {

    private ArrayList<Movie> movieList;
    private RecyclerView moviesRv;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        Log.i("MoviesActivity", "onCreate Call");
        getSupportActionBar().setTitle("Movies");
        handleAdd();
        setupData();
        setupMoviesRv();
    }

    private void deleteMessage(Movie movie) {
        MoviesApi movieApi = new MoviesApi();
        MoviesService movieService = movieApi.createMoviesService();
        Call<Void> call = movieService.deleteMessage(movie.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MoviesActivity.this, "SuccessFully Deleted", Toast.LENGTH_SHORT).show();
                fetchMovies();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MoviesActivity.this, "Failed On Item Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MoviesActivity", "onResume Call");
        fetchMovies();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddMovieActivity.class);
            startActivity(intent);
        });
    }

    private void fetchMovies() {
        MoviesApi movieApi = new MoviesApi();
        MoviesService movieService = movieApi.createMoviesService();
        Call<List<Movie>> call = movieService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movies = response.body();
                moviesAdapter.setData(movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MoviesActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupMoviesRv() {
        moviesRv = findViewById(R.id.movies_rv);
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movieList);
        moviesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Movie movie) {
                Toast.makeText(MoviesActivity.this, "On Item Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDeleted(Movie movie) {
                Toast.makeText(MoviesActivity.this, "On Item Delete", Toast.LENGTH_SHORT).show();
                deleteMessage(movie);
            }

            @Override
            public void onItemEdit(Movie movie) {
                Toast.makeText(MoviesActivity.this, "On Item Edit", Toast.LENGTH_SHORT).show();
            }
        });
        moviesRv.setAdapter(moviesAdapter);
    }

    private void setupData() {
        movieList = new ArrayList<>();

       /* Movie major = new Movie();
        major.name = "Major";
        major.imageUrl = "https://m.media-amazon.com/images/M/MV5BYWI2ODNjMDktZjcxNS00YThkLTljMDUtMGIyOTg3ZjY2MjlhXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_FMjpg_UX1000_.jpg";
        movieList.add(major);

        Movie bimbisara = new Movie();
        bimbisara.name = "Bimbisara";
        bimbisara.imageUrl = "https://m.media-amazon.com/images/M/MV5BZDViZWYyOGItOWY0NS00ODVmLWE3MzYtMWI2NDM5OTdkYTYyXkEyXkFqcGdeQXVyMTA3MDk2NDg2._V1_.jpg";
        movieList.add(bimbisara);*/
    }
}