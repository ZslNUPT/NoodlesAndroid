package com.njupt.sniper.app.common.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.adapter.BaseViewHolder;
import com.njupt.sniper.app.common.adapter.RecycleViewAdapter;
import com.njupt.sniper.app.common.customView.refresh.ProgressStyle;
import com.njupt.sniper.app.common.customView.refresh.XRecyclerView;
import com.njupt.sniper.app.common.presenter.BaseListPresenter;
import com.njupt.sniper.app.common.presenter.BasePresenter;
import com.njupt.sniper.app.common.view.BaseListView;
import com.njupt.sniper.mylibrary.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * @author chenshenglong
 * @date 16-8-29
 */
public abstract class BaseListFragment<E> extends BaseFragment<BaseListPresenter>
        implements BaseListView<E>, XRecyclerView.LoadingListener {

    @Bind(R.id.recycler_view)
    protected XRecyclerView xRecyclerView;

    protected RecycleViewAdapter<E> mAdapter;

    protected int PAGE = 1;

    protected Map<String, String> params = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void baseInit() {
        mPresenter.executeTask(getRequestParams());
    }

    @Override
    public void configureRecyclerView(List<E> items) {
        hideLoading();

        if (mAdapter == null) {
            mAdapter = new RecycleViewAdapter<E>(getItemLayout(), items) {
                @Override
                protected void convert(BaseViewHolder helper, E item) {
                    fitDates(helper, item);
                }
            };

            xRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
            xRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
            xRecyclerView.setLoadingListener(this);
            xRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

        mAdapter.setOnRecyclerViewItemChildClickListener((RecycleViewAdapter adapter, View v, int position) -> onItemClick(v, position));

    }

    protected void onItemClick(View v, int position) {

    }

    protected abstract void fitDates(BaseViewHolder helper, E item);

    protected abstract int getItemLayout();

    @Override
    protected View getLoadingTargetView() {
        return xRecyclerView;
    }


    @Override
    public void hasNoMoreData() {
        xRecyclerView.setNoMore(true);
        PAGE = PAGE - 1;
    }

    @Override
    public void loadMoreFinish(List dates) {
        xRecyclerView.loadMoreComplete();
        mAdapter.addDataAndNotify(dates);
    }

    @Override
    public void showRefreshFinish(List score) {
        xRecyclerView.refreshComplete();
        mAdapter.setNewData(score);
    }

    @Override
    public void showToastError() {
        xRecyclerView.reset();
        PAGE = PAGE - 1;
        ToastUtils.getInstance().showToast("网络环境不好");
    }

    @Override
    public void onRefresh() {
        PAGE = 1;
        mPresenter.executeTask(getRequestParams(), BaseListPresenter.RequestMode.REFRESH);
    }

    @Override
    public void onLoadMore() {
        PAGE = PAGE + 1;
        mPresenter.executeTask(getRequestParams(), BasePresenter.RequestMode.LOAD_MORE);
    }

    @Override
    protected Map<String, String> getRequestParams() {
        params.put("pageNum", PAGE + "");
        return params;
    }
}
