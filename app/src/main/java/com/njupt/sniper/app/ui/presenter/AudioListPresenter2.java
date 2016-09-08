package com.njupt.sniper.app.ui.presenter;

import android.app.Activity;

import com.njupt.sniper.app.common.view.BaseListView;
import com.njupt.sniper.app.model.entity.AudioEntity;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * author：Zsl
 * date：2016/9/8
 */
public class AudioListPresenter2 extends AudioListPresenter {
    public AudioListPresenter2(BaseListView<AudioEntity> view, Activity activity) {
        super(view, activity);
    }

    @Override
    protected Observable<List<AudioEntity>> getObservable(Map<String, String> params) {
//        return baseHttpMethods.resourcesMapToList(audioService.getAudios());
        return baseHttpMethods.resourcesMapToList(audioService.getAudios());
    }
}
