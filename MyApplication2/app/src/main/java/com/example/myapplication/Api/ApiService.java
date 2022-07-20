package com.example.myapplication.Api;

import com.example.myapplication.Model.Blog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.util.Currency;
import com.example.myapplication.sendID;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

//    http://localhost:8000/v1/blog/62adf969921fd22718c73ed3
    Gson gson= new GsonBuilder().setDateFormat("yyy-MM-dd HH:mm:ss").create();
    ApiService api = new Retrofit.Builder()
            .baseUrl("http://192.168.1.23:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("v1/blog/")
    Call<JsonArray> convertapi();

    @GET("v1/blog/{id}")
    Call<JsonObject> getoneBlog(@Path("id") String grid);

    @DELETE("v1/blog/{id}")
    Call<String> deleteblog(@Path("id") String grid);
    @POST("v1/blog")
    Call<Blog> sendpost(@Body Blog x);
    @PUT("v1/blog/{id}")
    Call<String> Update_post(@Path("id") String grid,@Body Blog x);
}
