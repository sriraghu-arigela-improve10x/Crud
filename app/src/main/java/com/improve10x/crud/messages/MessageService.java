package com.improve10x.crud.messages;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageService {
    @GET("sriraghuMessageHistory")
    Call<List<Message>> fetchMessages();

    @POST("sriraghuMessageHistory")
    Call<Message> createMessages(@Body Message message);

    @DELETE("sriraghuMessageHistory/{id}")
    Call<Void> deleteMessages(@Path("id")String id);
}
