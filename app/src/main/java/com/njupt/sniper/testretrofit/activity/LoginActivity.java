package com.njupt.sniper.testretrofit.activity;

import android.widget.EditText;

import com.njupt.sniper.testretrofit.R;
import com.njupt.sniper.testretrofit.entity.OAuthTokenEntity;
import com.njupt.sniper.testretrofit.http.HttpMethods;
import com.njupt.sniper.testretrofit.subscribers.ProgressSubscriber;
import com.njupt.sniper.testretrofit.subscribers.SimpleSubscriberOnNextListener;
import com.njupt.sniper.testretrofit.utils.AuthorityUtils;

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
        HttpMethods.getInstance(this).getTokenByPassword(new ProgressSubscriber(subscriberOnNextListener,this),userName.getText().toString(),password.getText().toString());
    }

}
