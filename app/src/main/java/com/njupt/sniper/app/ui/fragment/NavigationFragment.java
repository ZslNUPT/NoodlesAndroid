package com.njupt.sniper.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.fragment.BaseFragment;
import com.njupt.sniper.app.ui.presenter.NavigationPresenter;
import com.njupt.sniper.app.ui.viewInterface.NavigationView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author：Zsl
 * date：2016/8/31
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationView {

    @Bind(R.id.navigation_result)
    TextView result;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected NavigationPresenter getChildPresenter() {
        return new NavigationPresenter(this,getActivity());
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }


    @OnClick(R.id.navigation_click)
    public void onClick() {
        mPresenter.requestData();
    }

    @Override
    public void setResult(String text) {
        result.setText(text);
    }
}
