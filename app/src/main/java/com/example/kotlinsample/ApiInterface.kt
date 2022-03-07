package com.example.kotlinsample

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiInterface {

    @GET("users")
    suspend fun getUserList(

    ): Response<UserModel>



    companion object {
        operator fun invoke(): ApiInterface {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.callTimeout(2, TimeUnit.MINUTES)
            httpClient.connectTimeout(20, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.writeTimeout(30, TimeUnit.SECONDS);
            httpClient.addInterceptor(logging)
            return Retrofit.Builder()
                .baseUrl(APIClient.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(ApiInterface::class.java)
        }
    }


}
