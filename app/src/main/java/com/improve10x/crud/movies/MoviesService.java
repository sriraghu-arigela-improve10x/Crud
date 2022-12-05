package com.improve10x.crud.movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MoviesService {

    @GET("sriraghuMovies")
    Call<List<Movie>> fetchMovies();

    @POST("sriraghuMovies")
    Call<Movie> createMovie(@Body Movie movie);

    @DELETE("sriraghuMovies/{id}")
    Call<Void> deleteMessage(@Path("id") String id);
}
