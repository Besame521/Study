package com.example.myapplication.rxjava;

import com.example.myapplication.rxjava.bean.TestBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * 总结：
 * RxJava与retrofit结合使用与OkHttp结合使用的区别在于接口的返回值不同
 * OkHttp的返回值为Call
 * RxJava的返回值为Observable 注意包名：应该是rx.Observable
 */
public interface RxService {
    //注解关键字：
    //1、@Path("参数名") 填充url传参参数
    //2、@Query("参数名") 填充url问号传参的参数
    //3、@QueryMap 在url里填充若干个数不确定的参数
    //4、@Field 用于Post只提交一个参数
    //5、@Body 用于Post提交一个对象
    //6、@Url 用于重新定义接口地址
    //7、@FormUrlEncodede 使用@Field或@Body时要添加

    //GET的使用方法：
    //1、不传参
    @GET("/testGet1")
    Observable<TestBean> testGet1();

    //2、url传参（第一种方式）
    @GET("/testGet2/{id1}/{id2}")
    Observable<TestBean> testGet2(@Path("id1") String id1, @Path("id2") String id2);

    //3、url传参（第二种方式）
    @GET("/testGet3？id1=1&id2=1")
    Observable<TestBean> testGet3(@Query("id1") String id, @Query("id2") String id2);

    //4、传输多个不确定的参数
    @GET("/testGet4")
    Observable<TestBean> testGet4(@QueryMap Map<String, String> map);

    //另一种方式：n个固定参数+m个不定参数
    @GET("/testGet5")
    Observable<TestBean> testGet5(@Query("id1") String id1, @QueryMap Map<String, String> map);

    //POST的使用方法
    //1、需要补全url使用的注解使用@Path，问号后添加参数使用注解@Query
    //2、只POST一条数据
    @FormUrlEncoded
    @POST("/postTest")
    Observable<TestBean> testPost(@Field("id") String id);
    //3、POST一个Body
    @FormUrlEncoded
    @POST("/postTest2")
    Observable<TestBean> testPost1(@Body TestBean bean);
}
