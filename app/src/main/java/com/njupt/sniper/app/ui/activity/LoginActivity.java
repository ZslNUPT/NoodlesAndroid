package com.njupt.sniper.app.ui.activity;

import android.widget.EditText;

import com.njupt.sniper.app.model.entity.OAuthTokenEntity;
import com.njupt.sniper.app.model.http.BaseHttpMethods;
import com.njupt.sniper.app.model.subscribers.ProgressSubscriber;
import com.njupt.sniper.app.model.subscribers.SimpleSubscriberOnNextListener;
import com.njupt.sniper.app.utils.AuthorityUtils;
import com.njupt.sniper.mylibrary.ui.activity.BasicActivity;
import com.njupt.sniper.ui.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author：Zsl
 * date：2016/8/25
 */
public class LoginActivity extends BasicActivity {

    @Bind(R.id.login_username)
    EditText userName;

    @Bind(R.id.login_password)
    EditText password;

    SimpleSubscriberOnNextListener<OAuthTokenEntity> subscriberOnNextListener = new SimpleSubscriberOnNextListener<OAuthTokenEntity>() {
        @Override
        public void onNext(OAuthTokenEntity token) {
            AuthorityUtils.setAuthToken(token);
            AuthorityUtils.setIsLogin(true);
            finish();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.to_login)
    void login(){
        new BaseHttpMethods(this).getTokenByPassword(new ProgressSubscriber(subscriberOnNextListener,this),userName.getText().toString(),password.getText().toString());
    }

}
