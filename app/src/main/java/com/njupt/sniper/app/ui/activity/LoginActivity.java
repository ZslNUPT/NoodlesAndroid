package com.njupt.sniper.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.activity.BasicActivity;
import com.njupt.sniper.app.dagger.AccountModule;
import com.njupt.sniper.app.model.entity.OAuthTokenEntity;
import com.njupt.sniper.app.model.service.OAuthService;
import com.njupt.sniper.app.model.subscribers.ProgressSubscriber;
import com.njupt.sniper.app.model.subscribers.SimpleSubscriberOnNextListener;
import com.njupt.sniper.app.utils.AuthorityUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;

/**
 * author：Zsl
 * date：2016/8/25
 */
public class LoginActivity extends BasicActivity {

    @Bind(R.id.login_username)
    EditText userName;

    @Bind(R.id.login_password)
    EditText password;

    @Inject
    AccountModule accountConfig;

    @Inject
    OAuthService oAuthService;

    SimpleSubscriberOnNextListener<OAuthTokenEntity> subscriberOnNextListener = new SimpleSubscriberOnNextListener<OAuthTokenEntity>() {
        @Override
        public void onNext(OAuthTokenEntity token) {
            AuthorityUtils.setAuthToken(token);
            AuthorityUtils.setIsLogin(true);
            finish();
        }
    };

    public void getTokenByPassword(Subscriber<OAuthTokenEntity> subscriber, String username, String password) {

        Observable observable = oAuthService.getTokenByPassword(accountConfig.getClientId(), accountConfig.getClientSecret(),
                username, password, accountConfig.getGrantTypePassword());

        baseHttpMethods.toSubscribe(observable, subscriber);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getMainComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.to_login)
    void login() {
        getTokenByPassword(new ProgressSubscriber(subscriberOnNextListener, this), userName.getText().toString(), password.getText().toString());
    }

}
