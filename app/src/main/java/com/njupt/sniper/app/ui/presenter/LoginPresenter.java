package com.njupt.sniper.app.ui.presenter;

import android.app.Activity;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.presenter.BasePresenter;
import com.njupt.sniper.app.di.module.AccountModule;
import com.njupt.sniper.app.model.entity.OAuthTokenEntity;
import com.njupt.sniper.app.model.service.OAuthService;
import com.njupt.sniper.app.ui.viewInterface.LoginView;
import com.njupt.sniper.app.utils.AuthorityUtils;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * author：Zsl
 * date：2016/9/8
 */
public class LoginPresenter extends BasePresenter<OAuthTokenEntity> {

    @Inject
    OAuthService oAuthService;

    @Inject
    AccountModule accountModule;

    public LoginPresenter(LoginView view, Activity activity) {
        mView = view;
        mActivity = activity;
    }

    @Override
    public void inject() {
        MyApplication.getMainComponent().inject(this); // 应用注入
    }

    @Override
    protected Observable<OAuthTokenEntity> getObservable(Map params) {
       return oAuthService.getTokenByPassword(accountModule.getClientId(), accountModule.getClientSecret(),
               ((LoginView)mView).getUserName(), ((LoginView)mView).getPassword(), accountModule.getGrantTypePassword());
    }

    @Override
    protected void onAllSuccess(OAuthTokenEntity oAuthTokenEntity) {
        AuthorityUtils.setAuthToken(oAuthTokenEntity);
        AuthorityUtils.setIsLogin(true);
        mActivity.finish();
    }

}
