package com.njupt.sniper.testretrofit.activity;

import android.widget.TextView;

import com.njupt.sniper.testretrofit.R;
import com.njupt.sniper.testretrofit.entity.AudioEntity;
import com.njupt.sniper.testretrofit.entity.StaticsEntity;
import com.njupt.sniper.testretrofit.http.NavigationHttpMethods;
import com.njupt.sniper.testretrofit.subscribers.ProgressSubscriber;
import com.njupt.sniper.testretrofit.subscribers.SimpleSubscriberOnNextListener;

import org.springframework.hateoas.Resources;

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

    SimpleSubscriberOnNextListener<Resources<AudioEntity>> subscriberOnNextListener2 = new SimpleSubscriberOnNextListener<Resources<AudioEntity>>() {
        @Override
        public void onNext(Resources<AudioEntity> audioEntityList) {
       String s=audioEntityList.getData().get(0).getAudio_url();
            resultTV.setText(s);

        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    //获取统计数据
    @OnClick(R.id.get_statics)
    public void getStatics() {
//        new NavigationHttpMethods(this).getStatics(new ProgressSubscriber(subscriberOnNextListener,this));
        new NavigationHttpMethods(this).getAudios(new ProgressSubscriber(subscriberOnNextListener2,this));
    }

}
