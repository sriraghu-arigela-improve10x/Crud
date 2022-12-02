package com.improve10x.crud.templates;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TemplateService {

    @GET("sriraghuTemplates")
    Call<List<Template>> fetchData();

    @POST("sriraghuTemplates")
    Call<Template> createTask(@Body Template template);

    @DELETE("sriraghuTemplates/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);
}
