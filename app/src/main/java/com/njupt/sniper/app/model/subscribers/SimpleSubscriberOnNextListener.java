package com.njupt.sniper.app.model.subscribers;

import com.njupt.sniper.mylibrary.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * author：Zsl
 * date：2016/8/25
 */
public abstract class SimpleSubscriberOnNextListener<T> implements SubscriberOnNextListener<T> {
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.getInstance().showToast("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtils.getInstance().showToast("网络中断，请检查您的网络状态");
        } else {
           // ToastUtils.getInstance().showToast(e.getMessage());
        }
    }
}
