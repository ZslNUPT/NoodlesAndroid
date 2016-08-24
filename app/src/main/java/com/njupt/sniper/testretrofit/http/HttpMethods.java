package com.njupt.sniper.testretrofit.http;


import com.njupt.sniper.testretrofit.entity.GankBeautyResult;
import com.njupt.sniper.testretrofit.entity.HttpResult;
import com.njupt.sniper.testretrofit.entity.StaticesEntity;
import com.njupt.sniper.testretrofit.entity.Subject;
import com.njupt.sniper.testretrofit.entity.Token;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author：Zsl
 * date：2016/8/23
 */
public class HttpMethods {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    public static final String BASE_URL2 = "http://gank.io/api/";
    public static final String BASE_URL3 = "http://192.168.1.6:9000/";

    private static final int DEFAULT_TIMEOUT = 5;



    //构造方法私有
    private HttpMethods() {

    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     * @param subscriber  由调用者传过来的观察者对象
     * @param start 起始位置
     * @param count 获取长度
     */
    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

         MovieService movieService = retrofit.create(MovieService.class);

        Observable observable = movieService.getTopMovie(start, count)
                .map(new HttpResultFunc<List<Subject>>());

        toSubscribe(observable, subscriber);
    }

    public void getData(Subscriber<GankBeautyResult> subscriber){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient httpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL2)
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Observable observable = movieService.getBeauties(10, 1);

        toSubscribe(observable, subscriber);
    }

    public void getToken(Subscriber<Token> subscriber){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient httpClient = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL3)
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Observable observable = movieService.getToken("stu-app","rSbnsVdrC3o3CjChXUXkPFBdi4qO8cZ8","13260875986","123456","password");

        toSubscribe(observable, subscriber);
    }

    public void getStatics(Subscriber<StaticesEntity> subscriber, final String token){

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL3)
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Observable observable = movieService.getStatics("Bearer "+token);

        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
         o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCount() == 0) {
                throw new ApiException(100);
            }
            return httpResult.getSubjects();
        }
    }

}
