package com.njupt.sniper.app;

import android.app.Application;

import com.njupt.sniper.app.ui.fragment.NavigationFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author：Zsl
 * date：2016/9/1
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Application getAppLication();


}