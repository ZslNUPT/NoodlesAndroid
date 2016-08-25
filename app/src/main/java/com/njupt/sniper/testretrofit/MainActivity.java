package com.njupt.sniper.testretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.njupt.sniper.testretrofit.entity.StaticsEntity;
import com.njupt.sniper.testretrofit.entity.OAuthTokenEntity;
import com.njupt.sniper.testretrofit.http.HttpMethods;
import com.njupt.sniper.testretrofit.subscribers.ProgressSubscriber;
import com.njupt.sniper.testretrofit.subscribers.SubscriberOnNextListener;
import com.njupt.sniper.testretrofit.utils.AuthorityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.result_TV)
    TextView resultTV;


    SubscriberOnNextListener<OAuthTokenEntity> subscriberOnNextListener = new SubscriberOnNextListener<OAuthTokenEntity>() {
        @Override
        public void onNext(OAuthTokenEntity token) {
            resultTV.setText(token.access_token);
            AuthorityUtils.setAuthToken(token);
        }

        @Override
        public void onError(String errorMessage) {
            resultTV.setText(errorMessage);
        }
    };

    SubscriberOnNextListener<StaticsEntity> subscriberOnNextListener2 = new SubscriberOnNextListener<StaticsEntity>() {
        @Override
        public void onNext(StaticsEntity staticsEntity) {
            resultTV.setText(staticsEntity.resume_rank.share_title);
        }

        @Override
        public void onError(String errorMessage) {
            resultTV.setText(errorMessage);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    //获取token
    @OnClick(R.id.get_token)
    public void getToken() {
        HttpMethods.getInstance().getTokenByPassword(new ProgressSubscriber(subscriberOnNextListener, MainActivity.this),"13260875986","123456");
    }

    //获取统计数据
    @OnClick(R.id.get_statics)
    public void getStatics() {
        HttpMethods.getInstance().getStatics(new ProgressSubscriber(subscriberOnNextListener2, MainActivity.this));
    }

}
