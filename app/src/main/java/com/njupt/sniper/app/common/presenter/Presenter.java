package com.njupt.sniper.app.common.presenter;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.di.module.HttpMethodsModule;

import javax.inject.Inject;

/**
 * author：Zsl
 * date：2016/9/8
 */
public class Presenter {
    //dagger2好像无法注入泛型类

    @Inject
    public HttpMethodsModule baseHttpMethods;

    public void inject() {
        MyApplication.getMainComponent().inject(this);
    }
}
