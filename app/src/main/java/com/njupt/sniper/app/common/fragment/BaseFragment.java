package com.njupt.sniper.app.common.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.BaseView;
import com.njupt.sniper.app.common.activity.BaseActivity;
import com.njupt.sniper.app.common.loading.VaryViewHelperController;
import com.njupt.sniper.app.common.presenter.Presenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author：Zsl
 * date：2016/8/31
 */
public abstract class BaseFragment<T extends Presenter> extends Fragment implements BaseView {

    //与Fragment绑定的activity对象
    protected BaseActivity mActivity;
    //当前View的Presenter
    protected T mPresenter;
    private View contentView;
    //通用loading页error页等的控制器
    private VaryViewHelperController mVaryViewHelperController;

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    private TextView mTitleView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (contentView == null) {
            contentView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, contentView);

            if (null == mVaryViewHelperController)
                mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
            if (null == mPresenter)
                mPresenter = getChildPresenter();

            if (null != mPresenter)
                mPresenter.inject();

            baseInit();

        } else {
            ViewGroup parent = (ViewGroup) contentView.getParent();
            if (parent != null) {
                parent.removeView(contentView);
            }
        }

        return contentView;

    }

    protected abstract void baseInit();

    //获取布局文件ID
    protected abstract int getLayoutId();

    protected void setToolbar(boolean isNeedBackImg, String titleContent) {
        if (mToolbar != null) {
            if (isNeedBackImg) {
                mToolbar.setNavigationIcon(R.mipmap.ico_back);
                mToolbar.setNavigationOnClickListener(v -> onBackClick());
            }
            if (!TextUtils.isEmpty(titleContent)) {
                mTitleView = new TextView(mActivity);
                mTitleView.setTextSize(18);
                mTitleView.setTextColor(Color.WHITE);
                mTitleView.setText(titleContent);
                Toolbar.LayoutParams params = new Toolbar.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
                mToolbar.addView(mTitleView, params);
            }
        }
    }

    protected void onBackClick() {

    }

    protected abstract T getChildPresenter();

    protected  View getLoadingTargetView(){
        return contentView;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (null != this.getView()) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mPresenter)
            mPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mPresenter)
            mPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (null != mPresenter)
            mPresenter.destroy();
    }

    @Override
    public BaseActivity getContext() {
        return mActivity;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Google bug
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showLoading() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showLoading();
    }

    @Override
    public void hideLoading() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.restore();
    }

    @Override
    public void showNetError() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showNetworkError(v -> {
            showLoading();
            mPresenter.requestData(getRequestParams());
        });
    }

    @Override
    public void hasNoMoreData() {

    }

    @Override
    public void loadMoreFinish(List dates) {

    }

    @Override
    public void showRefreshFinish(List score) {

    }

    @Override
    public void showToastError() {

    }

    protected String getRequestParams() {
        return null;
    }

    @Override
    public void showEmptyView(String msg) {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showEmpty(msg);
    }
}
