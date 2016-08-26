package com.njupt.sniper.testretrofit.activity;

import android.widget.TextView;

import com.njupt.sniper.testretrofit.R;
import com.njupt.sniper.testretrofit.entity.StaticsEntity;
import com.njupt.sniper.testretrofit.http.NavigationHttpMethods;
import com.njupt.sniper.testretrofit.subscribers.ProgressSubscriber;
import com.njupt.sniper.testretrofit.subscribers.SimpleSubscriberOnNextListener;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BasicActivity {

    @Bind(R.id.result_TV)
    TextView resultTV;

    SimpleSubscriberOnNextListener<StaticsEntity> subscriberOnNextListener = new SimpleSubscriberOnNextListener<StaticsEntity>() {
        @Override
        public void onNext(StaticsEntity staticsEntity) {
            resultTV.setText(staticsEntity.resume_rank.share_title);
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    //获取统计数据
    @OnClick(R.id.get_statics)
    public void getStatics() {
        new NavigationHttpMethods(this).getStatics(new ProgressSubscriber(subscriberOnNextListener,this));
    }

}
