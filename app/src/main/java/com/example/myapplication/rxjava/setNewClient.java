package com.example.myapplication.rxjava;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class setNewClient {

    //retrofit可以设置自定义的okHttp的Client
    OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .readTimeout(8, TimeUnit.SECONDS)
            .connectTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .addInterceptor(new MyInterceptor())
            .retryOnConnectionFailure(true)
            .build();

    //retrofit自定义client
    Retrofit retrofit1 = new Retrofit
            .Builder()
            .client(client)
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
