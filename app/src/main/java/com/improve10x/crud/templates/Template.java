package com.improve10x.crud.templates;

import com.google.gson.annotations.SerializedName;

public class Template {
    @SerializedName("_id")
    String id;

    @SerializedName("title")
    String title;

    @SerializedName("messageText")
    String messageText;
}
