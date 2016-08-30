package com.njupt.sniper.testretrofit.http;


import android.app.Activity;
import android.content.Intent;

import com.njupt.sniper.mylibrary.utils.ToastUtils;
import com.njupt.sniper.testretrofit.activity.LoginActivity;
import com.njupt.sniper.testretrofit.entity.OAuthTokenEntity;
import com.njupt.sniper.testretrofit.http.service.OAuthService;
import com.njupt.sniper.testretrofit.http.service.ServiceGenerator;
import com.njupt.sniper.testretrofit.utils.AuthorityUtils;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * author：Zsl
 * date：2016/8/23
 */
public class BaseHttpMethods {

    private final String clientId = "stu-app";
    private final String clientSecret = "rSbnsVdrC3o3CjChXUXkPFBdi4qO8cZ8";
    private final String grantTypePassword = "password";
    private final String grantTypeRefreshToken = "refresh_token";

    private Activity activity;

    //构造方法私有
    public BaseHttpMethods(Activity activity) {
        this.activity = activity;
    }

    public void getTokenByPassword(Subscriber<OAuthTokenEntity> subscriber, String username, String password) {

        OAuthService movieService = ServiceGenerator.createService(OAuthService.class,false);

        Observable observable = movieService.getTokenByPassword(clientId, clientSecret, username, password, grantTypePassword);

        toSubscribe(observable, subscriber);
    }

    public Observable<OAuthTokenEntity> refreshOAuthToken(String refreshToken) {

        OAuthService movieService = ServiceGenerator.createService(OAuthService.class,false);

        Observable<OAuthTokenEntity> observable = movieService.getTokenByRefreshToken(clientId, clientSecret, grantTypeRefreshToken, refreshToken);

        return observable;
    }

    public <T> Observable<List<T>> pagedResourcesMapToList(Observable<PagedResources<T>> observable){
       return observable.map(new Func1<PagedResources<T>, List<T>>() {
            @Override
            public List<T> call(PagedResources<T> staticsEntities) {
                return staticsEntities.getData();
            }
        });
    }

    public <T> Observable<List<T>> resourcesMapToList(Observable<Resources<T>> observable){
        return observable.map(new Func1<Resources<T>, List<T>>() {
            @Override
            public List<T> call(Resources<T> staticsEntities) {
                return staticsEntities.getData();
            }
        });
    }

    public <T> void toSubscribe(final Observable<T> o, Subscriber<T> s) {

        o.retry(new Func2<Integer, Throwable, Boolean>() {//设置请求次数
                    @Override
                    public Boolean call(Integer integer, Throwable throwable) {
                        if (throwable instanceof SocketTimeoutException && integer < 2)
                            return true;
                        else
                            return false;
                    }
                }).retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
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


}
