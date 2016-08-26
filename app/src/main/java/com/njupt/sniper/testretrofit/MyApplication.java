package com.njupt.sniper.testretrofit;

import android.app.Application;

import com.njupt.sniper.mylibrary.utils.ToastUtils;

/**
 * author：Zsl
 * date：2016/8/25
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ToastUtils.init(this);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
