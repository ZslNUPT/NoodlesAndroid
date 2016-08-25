package com.njupt.sniper.testretrofit;

import android.app.Application;

import com.njupt.sniper.mylibrary.utils.ToastUtils;


/**
 * Created by _SOLID
 * Date:2016/5/16
 * Time:11:46
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
