package com.njupt.sniper.app.ui.fragment;

import android.widget.TextView;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.fragment.BasicFragment;
import com.njupt.sniper.app.model.entity.StaticsEntity;
import com.njupt.sniper.app.model.service.NavigationService;
import com.njupt.sniper.app.model.subscribers.ProgressSubscriber;
import com.njupt.sniper.app.model.subscribers.SimpleSubscriberOnNextListener;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author：Zsl
 * date：2016/8/31
 */
public class NavigationFragment extends BasicFragment {

    @Inject
    NavigationService navigationService;

    @Bind(R.id.navigation_result)
    TextView result;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @OnClick(R.id.navigation_click)
    public void onClick() {
        baseHttpMethods.toSubscribe(navigationService.getStatics(), new ProgressSubscriber<>(new SimpleSubscriberOnNextListener<StaticsEntity>() {
            @Override
            public void onNext(StaticsEntity staticsEntity) {
                result.setText(staticsEntity.resume_rank.share_content);
            }
        }, getActivity()));
    }
}
