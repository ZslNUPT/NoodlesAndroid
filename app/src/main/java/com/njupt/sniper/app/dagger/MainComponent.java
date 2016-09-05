package com.njupt.sniper.app.dagger;

import com.njupt.sniper.app.MyApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author：Zsl
 * date：2016/9/5
 */
@Singleton
@Component(modules = {AppModule.class, AccountModule.class,HttpMethodsModule.class,ServiceModule.class})
public interface MainComponent extends BaseComponent{
    final class Initializer {
        private Initializer() {
        } // No instances.

        // 初始化组件
        public static MainComponent init(MyApplication app) {
            return DaggerMainComponent.builder()
                    .appModule(new AppModule(app))
                    .accountModule(new AccountModule(app))
                    .httpMethodsModule(new HttpMethodsModule())
                    .build();
        }
    }

}
