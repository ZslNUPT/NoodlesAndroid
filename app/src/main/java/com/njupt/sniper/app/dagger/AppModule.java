package com.njupt.sniper.app.dagger;

import com.njupt.sniper.app.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author：Zsl
 * date：2016/9/5
 */
@Module
public class AppModule {
    private final MyApplication mApplication;

    public AppModule(MyApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    protected MyApplication myApplication() {
        return mApplication;
    }


}
