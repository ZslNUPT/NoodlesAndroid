package com.njupt.sniper.app.dagger;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
/**
 * author：Zsl
 * date：2016/9/6
 */
@PerActivity
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    protected Activity provideActivity() {
        return this.activity;
    }
}
