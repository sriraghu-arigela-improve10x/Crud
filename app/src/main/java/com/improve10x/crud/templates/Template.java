package com.improve10x.crud.templates;

import com.google.gson.annotations.SerializedName;

public class Template {
    @SerializedName("_id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("messageText")
    public String messageText;
}
