package com.improve10x.crud.movies;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("_id")
    public String id;
    public String movieId;

    @SerializedName("name")
    public String name;

    @SerializedName("seriesId")
    public String movieSeriesId;

    @SerializedName("imageUrl")
    public String imageUrl;

    @SerializedName("description")
    public String description;
}
