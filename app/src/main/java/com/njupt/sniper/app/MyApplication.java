package com.njupt.sniper.app;

import android.app.Application;

import com.njupt.sniper.app.di.MainComponent;
import com.njupt.sniper.mylibrary.utils.ToastUtils;

/**
 * author：Zsl
 * date：2016/8/25
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ToastUtils.init(this);

        mainComponent = MainComponent.Initializer.init(mInstance);

    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }
}
