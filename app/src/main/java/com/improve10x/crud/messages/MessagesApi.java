package com.improve10x.crud.messages;

import com.improve10x.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessagesApi {

    public MessageService createMessageService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessageService messageService = retrofit.create(MessageService.class);
        return messageService;
    }
}
