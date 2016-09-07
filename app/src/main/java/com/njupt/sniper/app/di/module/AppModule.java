package com.njupt.sniper.app.di.module;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.di.scope.AppScope;

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
    @AppScope
    protected MyApplication myApplication() {
        return mApplication;
    }


}
