package com.kerimov.adee.apigenerator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static final String BASE_URL_POSTS = "https://jsonplaceholder.typicode.com/";
    private static final String BASE_URL_WEATHER = "https://api.openweathermap.org/data/2.5/";
    private Retrofit mRetrofit;

    public NetworkService(int baseUrl) {
        if (baseUrl == 0) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_POSTS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        else {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_WEATHER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

//    public static NetworkService getInstance() {
//        if (mInstance == null) {
//            mInstance = new NetworkService();
//        }
//        return mInstance;
//    }

    public JsonApiPlaceholder getJSONApi() {

        return mRetrofit.create(JsonApiPlaceholder.class);
    }
}
