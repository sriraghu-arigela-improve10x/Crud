package com.improve10x.crud;

import com.google.gson.annotations.SerializedName;

public class Template {
    public String title;

    @SerializedName("messageText")
    public String message;
}
