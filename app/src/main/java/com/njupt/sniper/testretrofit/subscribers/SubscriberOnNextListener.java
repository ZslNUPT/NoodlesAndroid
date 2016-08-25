package com.njupt.sniper.testretrofit.subscribers;

import android.app.Activity;

/**
 * author：Zsl
 * date：2016/8/23
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onError(Throwable e, Activity activity);
}
