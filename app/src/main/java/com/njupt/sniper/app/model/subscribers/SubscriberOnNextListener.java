package com.njupt.sniper.app.model.subscribers;

/**
 * author：Zsl
 * date：2016/8/23
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onError(Throwable e);
}
