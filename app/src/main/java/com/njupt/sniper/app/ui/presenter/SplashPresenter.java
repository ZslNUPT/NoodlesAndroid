package com.njupt.sniper.app.ui.presenter;

import android.app.Activity;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.presenter.BasePresenter;
import com.njupt.sniper.app.di.module.AccountModule;
import com.njupt.sniper.app.model.entity.OAuthTokenEntity;
import com.njupt.sniper.app.model.service.OAuthService;
import com.njupt.sniper.app.ui.viewInterface.SplashView;
import com.njupt.sniper.app.utils.AuthorityUtils;
import com.njupt.sniper.app.utils.IntentStartUtils;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * author：Zsl
 * date：2016/9/8
 */
public class SplashPresenter extends BasePresenter<OAuthTokenEntity> {
    @Inject
    OAuthService oAuthService;

    @Inject
    AccountModule accountModule;

    private final String GUEST_NAME = "Guest";

    private final String GUEST_PASSWORD = "888888";

    public SplashPresenter(SplashView splashView, Activity activity) {
        mView = splashView;
        mActivity = activity;
    }

    @Override
    public void inject() {
        MyApplication.getMainComponent().inject(this); // 应用注入
    }

    @Override
    protected Observable<OAuthTokenEntity> getObservable(Map params) {
        return oAuthService.getTokenByPassword(accountModule.getClientId(), accountModule.getClientSecret(),
                GUEST_NAME, GUEST_PASSWORD, accountModule.getGrantTypePassword());
    }

    @Override
    protected void onAllSuccess(OAuthTokenEntity oAuthTokenEntity) {
        AuthorityUtils.setAuthToken(oAuthTokenEntity);
        AuthorityUtils.setIsLogin(true);
        IntentStartUtils.readyGoThenKillHomeActivity(mActivity);
    }

    @Override
    protected void onFail() {

    }
}
