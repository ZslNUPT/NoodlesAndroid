package com.njupt.sniper.testretrofit.http;

import android.app.Activity;

import com.njupt.sniper.testretrofit.entity.StaticsEntity;
import com.njupt.sniper.testretrofit.http.service.NavigationService;
import com.njupt.sniper.testretrofit.http.service.ServiceGenerator;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Zsl
 * date：2016/8/26
 */
public class NavigationHttpMethods extends BaseHttpMethods {

    public NavigationHttpMethods(Activity activity) {
        super(activity);
    }

    public void getStatics(Subscriber<StaticsEntity> subscriber) {

        NavigationService navigationService = ServiceGenerator.createService(NavigationService.class, false);

        Observable observable = navigationService.getStatics();

        toSubscribe(observable, subscriber);
    }

}
