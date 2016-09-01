package com.njupt.sniper.app;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author：Zsl
 * date：2016/9/1
 */
@Module
public class AppModule {

    private MyApplication application;

    public AppModule(MyApplication application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return application;
    }

}
