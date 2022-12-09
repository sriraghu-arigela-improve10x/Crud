package com.improve10x.crud.movies;

import com.improve10x.crud.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MoviesService {

    @GET(Constants.MOVIES_END_POINT)
    Call<List<Movie>> fetchMovies();

    @POST(Constants.MOVIES_END_POINT)
    Call<Movie> createMovie(@Body Movie movie);

    @DELETE(Constants.MOVIES_END_POINT + "/{id}")
    Call<Void> deleteMovie(@Path("id") String id);
}
