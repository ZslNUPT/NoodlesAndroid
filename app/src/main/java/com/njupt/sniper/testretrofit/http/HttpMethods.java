package com.njupt.sniper.testretrofit.http;


import android.app.Activity;
import android.content.Intent;

import com.njupt.sniper.mylibrary.utils.ToastUtils;
import com.njupt.sniper.testretrofit.activity.LoginActivity;
import com.njupt.sniper.testretrofit.entity.HttpResult;
import com.njupt.sniper.testretrofit.entity.OAuthTokenEntity;
import com.njupt.sniper.testretrofit.entity.StaticsEntity;
import com.njupt.sniper.testretrofit.utils.AuthorityUtils;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author：Zsl
 * date：2016/8/23
 */
public class HttpMethods {

    private final String clientId = "stu-app";
    private final String clientSecret = "rSbnsVdrC3o3CjChXUXkPFBdi4qO8cZ8";
    private final String grantTypePassword = "password";
    private final String grantTypeRefreshToken = "refresh_token";

    private Activity activity;

    //构造方法私有
    private HttpMethods(Activity activity) {
        this.activity = activity;
    }

    //获取单例
    public static HttpMethods getInstance(Activity activity) {
        return new HttpMethods(activity);
    }

    //获取单例
    public static HttpMethods getInstance() {
        return new HttpMethods(null);
    }

    public void getTokenByPassword(Subscriber<OAuthTokenEntity> subscriber, String username, String password) {

        OAuthService movieService = ServiceGenerator.createService(OAuthService.class);

        Observable observable = movieService.getTokenByPassword(clientId, clientSecret, username, password, grantTypePassword);

        toSubscribe(observable, subscriber);
    }

    public Observable<OAuthTokenEntity> refreshOAuthToken(String refreshToken) {

        OAuthService movieService = ServiceGenerator.createService(OAuthService.class);

        Observable<OAuthTokenEntity> observable = movieService.getTokenByRefreshToken(clientId, clientSecret, grantTypeRefreshToken, refreshToken);

        return observable;
    }

    public void getStatics(Subscriber<StaticsEntity> subscriber) {

        TestService testService = ServiceGenerator.createService(TestService.class,false);

        Observable observable = testService.getStatics();

        toSubscribe(observable, subscriber);
    }


    private <T> void toSubscribe(final Observable<T> o, Subscriber<T> s) {

        o.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if (throwable instanceof HttpException) {

                            //请求token无效
                            if (((HttpException) throwable).code() == 401) {
                                return refreshOAuthToken(AuthorityUtils.getAuthToken().refresh_token).doOnNext(new Action1<OAuthTokenEntity>() {
                                    @Override
                                    public void call(OAuthTokenEntity oAuthTokenEntity) {
                                        //refreshToken刷新成功
                                        AuthorityUtils.setAuthToken(oAuthTokenEntity);
                                    }
                                }).doOnError(new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {

                                        //refreshToken无效,跳登录
                                        if (((HttpException) throwable).code() == 400) {
                                            activity.startActivity(new Intent(activity, LoginActivity.class));
                                        }
                                    }
                                });
                            }

                            if (((HttpException) throwable).code() == 400) {
                                ToastUtils.getInstance().showToast("账号密码错误");
                            }
                        }
                        return Observable.error(throwable);
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.error != null) {
                throw new ApiException(httpResult.error, httpResult.error_description);
            }
            return httpResult.subject;
        }
    }

}
