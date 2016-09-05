package com.njupt.sniper.app.dagger;


import android.content.Intent;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.model.service.OAuthService;
import com.njupt.sniper.app.ui.activity.LoginActivity;
import com.njupt.sniper.app.utils.AuthorityUtils;
import com.njupt.sniper.mylibrary.utils.ToastUtils;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;

import java.net.SocketTimeoutException;
import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * author：Zsl
 * date：2016/8/23
 */

@Module
public class HttpMethodsModule {

    AccountModule accountConfig;

    OAuthService oAuthService;

    public HttpMethodsModule() {
    }

    @Inject
    public HttpMethodsModule(AccountModule accountConfig, OAuthService oAuthService) {
        this.accountConfig = accountConfig;
        this.oAuthService = oAuthService;
    }

    public <T> Observable<List<T>> pagedResourcesMapToList(Observable<PagedResources<T>> observable) {
        return observable.map(new Func1<PagedResources<T>, List<T>>() {
            @Override
            public List<T> call(PagedResources<T> staticsEntities) {
                return staticsEntities.getData();
            }
        });
    }

    public <T> Observable<List<T>> resourcesMapToList(Observable<Resources<T>> observable) {
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
                        return handleExc(throwable);
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public Observable<?> handleExc(Throwable throwable) {
        if (throwable instanceof HttpException) {
            //请求token无效
            if (((HttpException) throwable).code() == 401) {
                return oAuthService.getTokenByRefreshToken(accountConfig.getClientId(), accountConfig.getClientSecret(),
                        accountConfig.getGrantTypeRefreshToken(), AuthorityUtils.getAuthToken().refresh_token)
                        .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                            @Override
                            public Observable<?> call(Observable<? extends Throwable> observable) {
                                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                                    @Override
                                    public Observable<?> call(Throwable throwable) {
                                        //refreshToken无效,跳登录
                                        if (((HttpException) throwable).code() == 400) {
                                            MyApplication.getInstance().startActivity(new Intent(MyApplication.getInstance(), LoginActivity.class));
                                        }
                                        return Observable.just(AuthorityUtils.getAuthToken());
                                    }
                                });

                            }
                        });
            }

            if (((HttpException) throwable).code() == 400) {
                ToastUtils.getInstance().showToast("账号密码错误");
            }
        }
        return Observable.error(throwable);
    }


}
