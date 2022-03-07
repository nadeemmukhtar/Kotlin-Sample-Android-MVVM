package com.example.kotlinsample;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;
    public static String BaseUrl = "https://jsonplaceholder.typicode.com/";

       public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(interceptor);
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        clientBuilder.readTimeout(1, TimeUnit.MINUTES);

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        return retrofit;
    }





}
