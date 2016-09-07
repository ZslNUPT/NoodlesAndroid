package com.njupt.sniper.app.ui.presenter;

import android.app.Activity;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.presenter.BasePresenter;
import com.njupt.sniper.app.model.entity.StaticsEntity;
import com.njupt.sniper.app.model.service.NavigationService;
import com.njupt.sniper.app.model.subscribers.ProgressSubscriber;
import com.njupt.sniper.app.model.subscribers.SimpleSubscriberOnNextListener;
import com.njupt.sniper.app.ui.viewInterface.NavigationView;

import javax.inject.Inject;

/**
 * author：Zsl
 * date：2016/9/6
 */
public class NavigationPresenter extends BasePresenter {
    protected NavigationView mView;

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
    public void requestData(Object... o) {
        mView.showLoading();

        baseHttpMethods.toSubscribe(navigationService.getStatics(), new ProgressSubscriber<>(new SimpleSubscriberOnNextListener<StaticsEntity>() {
            @Override
            public void onNext(StaticsEntity staticsEntity) {
//                mView.setResult(staticsEntity.resume_rank.share_content);
                mView.showNetError();
//                mView.showEmptyView("d");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showNetError();
            }
        }, mActivity));
    }
}
