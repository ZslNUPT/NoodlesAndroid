package com.njupt.sniper.testretrofit.subscribers;

import android.app.Activity;

import com.njupt.sniper.mylibrary.utils.ToastUtils;
import com.njupt.sniper.testretrofit.http.HttpMethods;
import com.njupt.sniper.testretrofit.utils.AuthorityUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * author：Zsl
 * date：2016/8/25
 */
public abstract class SimpleSubscriberOnNextListener<T> implements SubscriberOnNextListener<T> {
    @Override
    public void onError(Throwable e, Activity activity) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.getInstance().showToast("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtils.getInstance().showToast("网络中断，请检查您的网络状态");
        } else if(e instanceof HttpException){
            if(((HttpException) e).code()==400) {
                ToastUtils.getInstance().showToast("账号或密码错误");
            }

            if(((HttpException) e).code()==401){
                HttpMethods.getInstance(activity).refreshOAuthToken(AuthorityUtils.getAuthToken().refresh_token);
            }

        }else{
            ToastUtils.getInstance().showToast(e.getMessage());
        }
    }
}
