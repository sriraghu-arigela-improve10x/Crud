package com.improve10x.crud.series;

import com.improve10x.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeriesItemsApi {

    SeriesItemsService createSeriesItemsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SeriesItemsService seriesService = retrofit.create(SeriesItemsService.class);
        return seriesService;
    }
}
