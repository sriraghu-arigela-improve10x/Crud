package com.improve10x.crud.series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SeriesItemsService {

    @GET("sriraghuSeries")
    Call<List<SeriesItem>> fetchSeries();

    @POST("sriraghuSeries")
    Call<SeriesItem> createSeries(@Body SeriesItem series);

    @DELETE("sriraghuSeries/{id}")
    Call<Void> deleteSeries(@Path("id") String id);
}
