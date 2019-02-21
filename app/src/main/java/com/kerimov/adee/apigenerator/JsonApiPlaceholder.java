package com.kerimov.adee.apigenerator;

import com.kerimov.adee.apigenerator.model.Comment;
import com.kerimov.adee.apigenerator.model.Post;
import com.kerimov.adee.apigenerator.model.Weather;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApiPlaceholder {
    @GET("posts")
    Call<List<Post>> postsList();

    @GET("posts/")
    Call<List<Post>> getPost(@Query("id") int[] id);

    @GET("comments/")
    Call<List<Comment>> getComment(@Query("id") int[] id);

    @GET("weather")
    Call<List<Weather>> getWeather(@Query("id") int[] id,@Query("appid") String appId);
}
