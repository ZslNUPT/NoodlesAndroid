package com.njupt.sniper.app;

import android.app.Application;
import android.content.Context;

import com.njupt.sniper.mylibrary.utils.ToastUtils;

/**
 * author：Zsl
 * date：2016/8/25
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ToastUtils.init(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static MyApplication get(Context context){
        return (MyApplication)context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }


}
