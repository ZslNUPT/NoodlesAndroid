package com.njupt.sniper.app;

import android.app.Application;

import com.njupt.sniper.app.model.http.BaseHttpMethods;
import com.njupt.sniper.app.model.http.NavigationHttpMethods;

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

    @Provides
    @Singleton
    public BaseHttpMethods baseHttpMethods(){
        return new BaseHttpMethods();
    }

    @Provides
    @Singleton
    public NavigationHttpMethods navigationHttpMethods(){
        return new NavigationHttpMethods();
    }

}
