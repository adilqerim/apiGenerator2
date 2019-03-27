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

public class NetworkService {
    private static NetworkService mInstance;
    private static Retrofit mRetrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";


    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(getHttpInterceptor(provideClient()))
                .build();
    }

    public static NetworkService getInstance(){
        if (mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }
    public OkHttpClient getHttpInterceptor(HttpLoggingInterceptor interceptor){
          final OkHttpClient.Builder builder =  new  OkHttpClient.Builder();
           builder.addInterceptor(new Interceptor() {
              @Override
              public Response intercept(Chain chain) throws IOException {
                  Request  original = chain.request();
                  Request request = original.newBuilder().method(original.method(),original.body()).build();
                   return chain.proceed(request);

              }
          }).addInterceptor(interceptor).writeTimeout(1,TimeUnit.MINUTES).readTimeout(1,TimeUnit.MINUTES)
                   .connectTimeout(1,TimeUnit.MINUTES);
            return builder.build();

    }

    public JsonPlaceHolderApi getJSONApi() {

        return mRetrofit.create(JsonPlaceHolderApi.class);
    }

    private HttpLoggingInterceptor provideClient(){
       HttpLoggingInterceptor inter =  new HttpLoggingInterceptor();
       inter.setLevel(HttpLoggingInterceptor.Level.BODY);
       return inter;
    }

}