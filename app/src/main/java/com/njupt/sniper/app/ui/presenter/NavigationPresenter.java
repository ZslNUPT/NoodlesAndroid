package com.njupt.sniper.app.ui.presenter;

import android.app.Activity;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.presenter.BasePresenter;
import com.njupt.sniper.app.model.entity.StaticsEntity;
import com.njupt.sniper.app.model.service.NavigationService;
import com.njupt.sniper.app.ui.viewInterface.NavigationView;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * author：Zsl
 * date：2016/9/6
 */
public class NavigationPresenter extends BasePresenter<StaticsEntity> {

    @Inject
    NavigationService navigationService;

    public NavigationPresenter(NavigationView view, Activity activity) {
        mView = view;
        mActivity = activity;
    }

    @Override
    public void inject() {
        MyApplication.getMainComponent().inject(this); // 应用注入
    }

    @Override
    protected Observable getObservable(Map params) {
       return navigationService.getStatics();
    }

    @Override
    protected void onAllSuccess(StaticsEntity staticsEntity) {
        ((NavigationView)mView).setResult(staticsEntity.resume_rank.share_content);
    }
}
