package com.njupt.sniper.app.ui.fragment;

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
    protected void baseInit() {
        setToolbar(true, "首页");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected NavigationPresenter getChildPresenter() {
        return new NavigationPresenter(this,getActivity());
    }

    @Override
    protected View getLoadingTargetView() {
        return result;
    }

    @OnClick(R.id.navigation_click)
    public void onClick() {
        mPresenter.executeTask();
    }

    @Override
    public void setResult(String text) {
        result.setText(text);
    }
}
