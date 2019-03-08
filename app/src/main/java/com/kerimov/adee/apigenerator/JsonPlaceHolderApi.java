package com.kerimov.adee.apigenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("/posts/")
    Call<List<Post>> getPost(@Query("id") int[] id);

    @GET("/comments/")
    Call<List<Comment>> getComment(@Query("id") int[] id);

    @GET("/comments/{id}")
    Call<Comment> getCommentById(@Path("id") int id);

    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") int id);

    @GET("/albums/")
    Call<List<Album>> getAlbum(@Query("id") int[] id);

    @GET("/photos/")
    Call<List<Photo>> getPhoto(@Query("id") int[] id);

    @GET("weather?appid=985601627df7745a7f4a0a1d8b7b4f4b")
    Call<List<Weather>> getWeather(@Query("id") int[] id);
}
