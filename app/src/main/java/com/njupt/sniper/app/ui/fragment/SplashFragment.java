package com.njupt.sniper.app.ui.fragment;

import android.os.SystemClock;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.fragment.BaseFragment;
import com.njupt.sniper.app.ui.presenter.SplashPresenter;
import com.njupt.sniper.app.ui.viewInterface.SplashView;
import com.njupt.sniper.app.utils.AuthorityUtils;
import com.njupt.sniper.app.utils.IntentStartUtils;

/**
 * author：Zsl
 * date：2016/9/8
 */
public class SplashFragment extends BaseFragment<SplashPresenter> implements SplashView {
    @Override
    protected void baseInit() {
        if (!AuthorityUtils.getIsLogin())
            mPresenter.executeTask();
        else {
            SystemClock.sleep(2000);
            IntentStartUtils.readyGoThenKillHomeActivity(mActivity);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected SplashPresenter getChildPresenter() {
        return new SplashPresenter(this, mActivity);
    }
}
