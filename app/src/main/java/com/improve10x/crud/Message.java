package com.improve10x.crud;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("name")
    public String title;
    @SerializedName("phoneNumber")
    public String phoneNumber;
    @SerializedName("messageText")
    public String message;
}
