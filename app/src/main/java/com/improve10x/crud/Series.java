package com.improve10x.crud;

import com.google.gson.annotations.SerializedName;

public class Series {
    @SerializedName("id")
    public String id;
    @SerializedName("title")
    public String title;
    @SerializedName("imageUrl")
    public String imageUrl;
}
