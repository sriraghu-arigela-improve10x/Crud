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
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends BaseActivity {

    private CrudService crudService;
    private ArrayList<Movie> movieList;
    private RecyclerView moviesRv;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        getSupportActionBar().setTitle("Movies");
        handleAdd();
        setupData();
        setupApiService();
        log("onCreate");
        setupMoviesRv();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void deleteMovie(Movie movie) {
        Call<Void> call = crudService.deleteMovie(movie.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("SuccessFully Deleted");
                fetchMovies();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed On Item Delete");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
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
        Call<List<Movie>> call = crudService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movies = response.body();
                moviesAdapter.setData(movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                showToast("Try Again");
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
                showToast("On Item Clicked");
            }

            @Override
            public void onItemDeleted(Movie movie) {
               showToast("On Item Delete");
                deleteMovie(movie);
            }

            @Override
            public void onItemEdit(Movie movie) {
                showToast("On Item Edit");
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