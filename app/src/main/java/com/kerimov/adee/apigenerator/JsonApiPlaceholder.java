package com.kerimov.adee.apigenerator;

import com.kerimov.adee.apigenerator.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApiPlaceholder {
    @GET("posts")
    Call<List<Post>> postsList();
}
