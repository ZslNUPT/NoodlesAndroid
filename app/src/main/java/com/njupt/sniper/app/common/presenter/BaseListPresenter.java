package com.njupt.sniper.app.common.presenter;

import android.app.Activity;

import com.njupt.sniper.app.common.BaseListView;
import com.njupt.sniper.app.model.subscribers.ProgressSubscriber;
import com.njupt.sniper.app.model.subscribers.SimpleSubscriberOnNextListener;

import java.util.List;

import rx.Observable;

/**
 * author：Zsl
 * date：2016/9/7
 */
public abstract class BaseListPresenter<T> extends BasePresenter {
    protected BaseListView mView;
    protected List<T> items;

    public BaseListPresenter(BaseListView view, Activity activity) {
        mView = view;
        mActivity = activity;
    }

   protected abstract Observable<List<T>> getData();

    @Override
    public void requestData(Object... o) {
        if(items!=null)  return;

        mView.showLoading();
        baseHttpMethods.toSubscribe(getData(), new ProgressSubscriber<>(new SimpleSubscriberOnNextListener<List<T>>() {

            @Override
            public void onNext(List<T> ts) {
                items=ts;
                mView.hideLoading();
                mView.configureRecyclerView(ts);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showNetError();
            }
        }, mActivity));
    }
}
