package com.njupt.sniper.app.di;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.presenter.Presenter;
import com.njupt.sniper.app.ui.presenter.AudioListPresenter;
import com.njupt.sniper.app.ui.presenter.LoginPresenter;
import com.njupt.sniper.app.ui.presenter.NavigationPresenter;

/**
 * author：Zsl
 * date：2016/9/5
 */
public interface BaseComponent {

    void inject(MyApplication myApplication);

    void inject(Presenter presenter);

    void inject(NavigationPresenter navigationPresenter);

    void inject(AudioListPresenter audioListPresenter);

    void inject(LoginPresenter loginPresenter);

}
