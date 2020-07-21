package com.example.myapplication.rxjava;

import android.app.Activity;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.rxjava.bean.TestBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//为了防止内存泄漏，Activity 需继承自 RxAppCompatActivity RxFragmentActivity 或 RxActivity
//Fragment 需继承  RxFragment
public class RxJavaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建Retrofit对象，传入基本的请求url
        //创建过程采用的是创建者模式
        Retrofit retrofit = new Retrofit
                .Builder()
                //添加Gson解析，解析返回的Json数据
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("url")
                .build();
        //通过自定义的接口获取实例化的Service对象
        TestService testService = retrofit.create(TestService.class);
        Call<TestBean> call = testService.testGet1();
        //异步请求网络
        call.enqueue(new Callback<TestBean>() {
            @Override
            public void onResponse(Call<TestBean> call, Response<TestBean> response) {
                //请求成功
            }

            @Override
            public void onFailure(Call<TestBean> call, Throwable t) {
                //请求失败
            }
        });

        //通过自定义的接口获取实例化的Service对象
        retrofit.create(RxService.class)
                .testGet1()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestBean value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}