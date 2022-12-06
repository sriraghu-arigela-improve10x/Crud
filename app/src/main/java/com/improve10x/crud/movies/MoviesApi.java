package com.improve10x.crud.movies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesApi {

    public MoviesService createMoviesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://crudcrud.com/api/479dd07f8c1d482e9219f7dcb48e25f4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MoviesService movieService = retrofit.create(MoviesService.class);
        return movieService;
    }
}
