package com.njupt.sniper.app.di;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.di.module.AccountModule;
import com.njupt.sniper.app.di.module.AppModule;
import com.njupt.sniper.app.di.module.HttpMethodsModule;
import com.njupt.sniper.app.di.module.ServiceModule;
import com.njupt.sniper.app.di.scope.AppScope;

import dagger.Component;

/**
 * author：Zsl
 * date：2016/9/5
 */
@AppScope
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
                    .serviceModule(new ServiceModule())
                    .build();
        }
    }

}
