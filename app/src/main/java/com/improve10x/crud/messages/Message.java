package com.improve10x.crud.messages;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("_id")
    String id;
    @SerializedName("name")
    String title;
    @SerializedName("phoneNumber")
    String phoneNumber;
    @SerializedName("messageText")
    String messageText;

}
