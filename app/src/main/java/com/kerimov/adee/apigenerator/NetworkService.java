package com.kerimov.adee.apigenerator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static Retrofit mRetrofit;
    private static Retrofit mRetrofitWeather;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/";


    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitWeather = new Retrofit.Builder()
                .baseUrl(WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        if (mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public JsonPlaceHolderApi getJSONApi() {

        return mRetrofit.create(JsonPlaceHolderApi.class);
    }

    public JsonPlaceHolderApi getJSONApiWeather() {

        return mRetrofitWeather.create(JsonPlaceHolderApi.class);
    }

}