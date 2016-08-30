package com.njupt.sniper.testretrofit.http;

import android.app.Activity;

import com.njupt.sniper.testretrofit.entity.StaticsEntity;
import com.njupt.sniper.testretrofit.http.service.NavigationService;
import com.njupt.sniper.testretrofit.http.service.ServiceGenerator;

import org.springframework.hateoas.Resources;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Zsl
 * date：2016/8/26
 */
public class NavigationHttpMethods extends BaseHttpMethods {
    private NavigationService navigationService;

    public NavigationHttpMethods(Activity activity) {
        super(activity);
        navigationService = ServiceGenerator.createService(NavigationService.class, true);
    }

    public void getStatics(Subscriber<StaticsEntity> subscriber) {

        Observable observable = navigationService.getStatics();

        toSubscribe(observable, subscriber);
    }

    public void getAudios(Subscriber<Resources<StaticsEntity>> subscriber) {

        Observable observable = navigationService.getAudios();

        toSubscribe(observable, subscriber);
    }

}
