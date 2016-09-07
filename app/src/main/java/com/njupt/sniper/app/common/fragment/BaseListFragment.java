package com.njupt.sniper.app.common.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.BaseListView;
import com.njupt.sniper.app.common.adapter.BaseViewHolder;
import com.njupt.sniper.app.common.adapter.RecycleViewAdapter;
import com.njupt.sniper.app.common.presenter.BaseListPresenter;
import com.njupt.sniper.app.common.refresh.ProgressStyle;
import com.njupt.sniper.app.common.refresh.XRecyclerView;

import java.util.List;

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

    protected LinearLayoutManager layoutManager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        layoutManager = new LinearLayoutManager(getContext());
        mPresenter.requestData(getRequestParams());
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

        mAdapter.setOnRecyclerViewItemChildClickListener((RecycleViewAdapter adapter, View v,int position) -> onItemClick(v,position));

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
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
