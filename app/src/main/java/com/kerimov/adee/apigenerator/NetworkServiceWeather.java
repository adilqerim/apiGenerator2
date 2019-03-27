package com.kerimov.adee.apigenerator;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceWeather {

    private static NetworkServiceWeather mInstance;
    private static Retrofit mRetrofitWeather;
    private static final String WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/";


    private NetworkServiceWeather() {
        mRetrofitWeather = new Retrofit.Builder()
                .baseUrl(WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(getHttpInterceptor(provideClient()))
                .build();
    }

    public static NetworkServiceWeather getInstance(){
        if (mInstance == null){
            mInstance = new NetworkServiceWeather();
        }
        return mInstance;
    }
    public OkHttpClient getHttpInterceptor(HttpLoggingInterceptor interceptor){
        final OkHttpClient.Builder builder =  new  OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder().method(original.method(),original.body()).build();
                return chain.proceed(request);

            }
        }).addInterceptor(interceptor).writeTimeout(1,TimeUnit.MINUTES).readTimeout(1,TimeUnit.MINUTES)
                .connectTimeout(1,TimeUnit.MINUTES);
        return builder.build();

    }


    public JsonPlaceHolderApi getJSONApiWeather() {

        return mRetrofitWeather.create(JsonPlaceHolderApi.class);
    }
    private HttpLoggingInterceptor provideClient(){
        HttpLoggingInterceptor inter =  new HttpLoggingInterceptor();
        inter.setLevel(HttpLoggingInterceptor.Level.BODY);
        return inter;
    }
}
