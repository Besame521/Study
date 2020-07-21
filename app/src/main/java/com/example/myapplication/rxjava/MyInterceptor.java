package com.example.myapplication.rxjava;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 自定义拦截器
 */
public class MyInterceptor implements Interceptor {
    private static String TAG = "Test";
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求
        Request request = chain.request();
        Log.e(TAG, "request" + request.toString());
        long start = System.nanoTime();
        //通过chain获取响应的数据
        Response response = chain.proceed(chain.request());
        Log.e(TAG, "response" + response.toString());
        long end = System.nanoTime();
        Log.e(TAG, "response" + response.toString());

        MediaType mediaType = null;
        String content = "";
        if (response.body() != null){
            mediaType = response.body().contentType();
            content = response.body().string();
            Log.e(TAG, "response" + response.toString());
        }
        return response.newBuilder().body(ResponseBody.create(mediaType,content)).build();
    }
}
