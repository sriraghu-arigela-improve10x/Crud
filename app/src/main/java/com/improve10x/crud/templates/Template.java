package com.improve10x.crud.templates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Template implements Serializable {
    @SerializedName("_id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("messageText")
    public String messageText;
}
