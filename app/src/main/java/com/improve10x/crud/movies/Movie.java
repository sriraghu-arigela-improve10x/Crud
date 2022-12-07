package com.improve10x.crud.movies;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("_id")
    String id;
    String movieId;

    @SerializedName("name")
    String name;

    @SerializedName("seriesId")
    String movieSeriesId;

    @SerializedName("imageUrl")
    String imageUrl;

    @SerializedName("description")
    String description;
}
