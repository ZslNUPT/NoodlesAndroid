package com.njupt.sniper.app.common.presenter;


import android.app.Activity;

import com.njupt.sniper.app.common.view.BaseView;
import com.njupt.sniper.app.model.subscribers.ProgressSubscriber;
import com.njupt.sniper.app.model.subscribers.SimpleSubscriberOnNextListener;

import java.util.Map;

import rx.Observable;

/**
 * author：Zsl
 * date：2016/9/7
 */
public abstract class BasePresenter<T> extends Presenter {

    public Activity mActivity;

    protected RequestMode mode = RequestMode.FIRST;

    protected BaseView mView;

    public enum RequestMode {
        FIRST, LOAD_MORE, REFRESH
    }

    protected abstract Observable<T> getObservable(Map<String, String> params);

    public void requestData(Map<String, String> params) {
        mView.showLoading();

        requestData(params, RequestMode.FIRST);
    }

    public void requestData(Map<String, String> params, RequestMode mode) {
        if (null == getObservable(params)) {
            throw new IllegalArgumentException("no Observable");
        }

        this.mode = mode;

        baseHttpMethods.toSubscribe(getObservable(params), new ProgressSubscriber<>(new SimpleSubscriberOnNextListener<T>() {

            @Override
            public void onFinish() {
                mView.hideLoading();
            }

            @Override
            public void onNext(T t) {
                if (null != t) {
                    onAllSuccess(t);
                } else {
                    onFail();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                onFail();
            }
        }, mActivity));
    }

    protected void onAllSuccess(T t) {
    }

    protected void onFail() {
        mView.showNetError();
    }

    public void resume() {

    }

    public void pause() {

    }

    public void destroy() {

    }

}
