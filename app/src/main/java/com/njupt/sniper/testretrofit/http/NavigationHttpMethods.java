package com.njupt.sniper.testretrofit.http;

import android.app.Activity;

import com.njupt.sniper.testretrofit.entity.AudioEntity;
import com.njupt.sniper.testretrofit.entity.StaticsEntity;
import com.njupt.sniper.testretrofit.http.service.NavigationService;
import com.njupt.sniper.testretrofit.http.service.ServiceGenerator;

import java.util.List;

import rx.Subscriber;

/**
 * author：Zsl
 * date：2016/8/26
 */
public class NavigationHttpMethods extends BaseHttpMethods {
    private NavigationService navigationService;

    public NavigationHttpMethods(Activity activity) {
        super(activity);
        navigationService = ServiceGenerator.createService(NavigationService.class);
    }

    public void getStatics(Subscriber<StaticsEntity> subscriber) {

        toSubscribe(navigationService.getStatics(), subscriber);
    }

    public void getAudios(Subscriber<List<AudioEntity>> subscriber) {

        toSubscribe(resourcesMapToList(navigationService.getAudios()), subscriber);
    }

    public void getPagedAudioList(Subscriber<List<AudioEntity>> subscriber) {

        toSubscribe(pagedResourcesMapToList(navigationService.getPagedAudioList(0, 10)), subscriber);
    }

}
