package com.njupt.sniper.app.dagger;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.activity.BaseActivity;
import com.njupt.sniper.app.common.presenter.BasePresenter;
import com.njupt.sniper.app.ui.activity.LoginActivity;
import com.njupt.sniper.app.ui.presenter.NavigationPresenter;

/**
 * author：Zsl
 * date：2016/9/5
 */
public interface BaseComponent {

    void inject(MyApplication myApplication);

    void inject(BaseActivity basicActivity);

    void inject(LoginActivity basicActivity);

    void inject(NavigationPresenter navigationPresenter);

    void inject(BasePresenter basePresenter);

}
