package com.improve10x.crud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TemplateService {

    @GET("sriraghuTemplates")
    Call<List<Template>> fetchData();

    @POST("sriraghuTemplates")
    Call<Template> createTask(@Body Template template);
}
