package com.njupt.sniper.app.common.presenter;


import android.app.Activity;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.dagger.HttpMethodsModule;

import javax.inject.Inject;

/**
 * Created by Arron on 16/7/26.
 */
public class BasePresenter implements Presenter {

    @Inject
    public HttpMethodsModule baseHttpMethods;

    public Activity mActivity;

    @Override
    public void inject() {
        MyApplication.getMainComponent().inject(this);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void requestData(Object... o) {

    }
}
