package com.njupt.sniper.app.ui.presenter;

import android.app.Activity;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.view.BaseListView;
import com.njupt.sniper.app.common.presenter.BaseListPresenter;
import com.njupt.sniper.app.model.entity.AudioEntity;
import com.njupt.sniper.app.model.service.AudioService;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * author：Zsl
 * date：2016/9/7
 */
public class AudioListPresenter extends BaseListPresenter<AudioEntity> {
    @Inject
    AudioService audioService;

    public AudioListPresenter(BaseListView<AudioEntity> view, Activity activity) {
        super(view, activity);
    }

    @Override
    public void inject() {
        MyApplication.getMainComponent().inject(this); // 应用注入
    }

    @Override
    protected Observable<List<AudioEntity>> getObservable(Map<String, String> params) {
        return baseHttpMethods.pagedResourcesMapToList(audioService.getPagedAudioList(Integer.parseInt(params.get("pageNum")),10));
    }


}
