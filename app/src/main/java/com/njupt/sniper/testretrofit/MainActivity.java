package com.njupt.sniper.testretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.njupt.sniper.testretrofit.entity.StaticesEntity;
import com.njupt.sniper.testretrofit.entity.Token;
import com.njupt.sniper.testretrofit.http.HttpMethods;
import com.njupt.sniper.testretrofit.subscribers.ProgressSubscriber;
import com.njupt.sniper.testretrofit.subscribers.SubscriberOnNextListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.result_TV)
    TextView resultTV;

    private String myToken;

    SubscriberOnNextListener<Token> subscriberOnNextListener = new SubscriberOnNextListener<Token>() {
        @Override
        public void onNext(Token token) {
            myToken=token.access_token;
            resultTV.setText(token.access_token);
        }
    };
    SubscriberOnNextListener<StaticesEntity> subscriberOnNextListener2 = new SubscriberOnNextListener<StaticesEntity>() {
        @Override
        public void onNext(StaticesEntity staticesEntity) {
            resultTV.setText(staticesEntity.resume_rank.share_title);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.get_token)
    public void onClick() {
        getToken();
    }

    @OnClick(R.id.get_statics)
    public void onClick2() {
        getStatics();
    }

    //获取token
    private void getToken() {
        HttpMethods.getInstance().getToken(new ProgressSubscriber(subscriberOnNextListener, MainActivity.this));
    }

    //获取统计数据
    private void getStatics() {
        HttpMethods.getInstance().getStatics(new ProgressSubscriber(subscriberOnNextListener2, MainActivity.this),myToken);
    }
}
