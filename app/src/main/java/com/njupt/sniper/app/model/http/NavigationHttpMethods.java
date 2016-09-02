package com.njupt.sniper.app.model.http;

import com.njupt.sniper.app.model.entity.AudioEntity;
import com.njupt.sniper.app.model.entity.StaticsEntity;
import com.njupt.sniper.app.model.service.NavigationService;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import rx.Subscriber;

/**
 * author：Zsl
 * date：2016/8/26
 */
@Module
public class NavigationHttpMethods extends BaseHttpMethods {

    @Inject
    private NavigationService navigationService;

    public void getStatics(Subscriber<StaticsEntity> subscriber) {

        toSubscribe(navigationService.getStatics(), subscriber);
    }

    public void getAudios(Subscriber<List<AudioEntity>> subscriber) {

        toSubscribe(resourcesMapToList(navigationService.getAudios()), subscriber);
    }

    public void getPagedAudioList(Subscriber<List<AudioEntity>> subscriber,int page,int size) {

        toSubscribe(pagedResourcesMapToList(navigationService.getPagedAudioList(page, size)), subscriber);
    }

}
