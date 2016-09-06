package com.njupt.sniper.app.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.fragment.BaseFragment;
import com.njupt.sniper.app.common.presenter.Presenter;

/**
 * author：Zsl
 * date：2016/8/31
 */
public class SecFragment extends BaseFragment {
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected Presenter getChildPresenter() {
        return null;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
