package com.njupt.sniper.app.di;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.activity.BaseActivity;
import com.njupt.sniper.app.common.presenter.Presenter;
import com.njupt.sniper.app.ui.activity.LoginActivity;
import com.njupt.sniper.app.ui.presenter.AudioListPresenter;
import com.njupt.sniper.app.ui.presenter.NavigationPresenter;

/**
 * author：Zsl
 * date：2016/9/5
 */
public interface BaseComponent {

    void inject(MyApplication myApplication);

    void inject(BaseActivity basicActivity);

    void inject(LoginActivity basicActivity);

    void inject(Presenter presenter);

    void inject(NavigationPresenter navigationPresenter);

    void inject(AudioListPresenter audioListPresenter);

}
