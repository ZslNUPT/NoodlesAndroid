package com.njupt.sniper.app.common.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.BaseListView;
import com.njupt.sniper.app.common.RecyclerDecoration;
import com.njupt.sniper.app.common.presenter.BaseListPresenter;

import java.util.List;

import butterknife.Bind;

/**
 * @author chenshenglong
 * @date 16-8-29
 */
public abstract class ItemListFragment<E, S extends RecyclerView.Adapter> extends BaseFragment<BaseListPresenter> implements BaseListView<E> {

    @Bind(R.id.recycler_view)
    protected RecyclerView recyclerView;

    protected LinearLayoutManager layoutManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.requestData(getRequestParams());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        layoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    public void configureRecyclerView(List<E> items) {
        //线性布局
        recyclerView.setLayoutManager(layoutManager);
        //设置 分割线
        recyclerView.addItemDecoration(new RecyclerDecoration(getContext(), RecyclerDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(createAdapter(items));
    }

    protected abstract S createAdapter(final List<E> items);

    @Override
    protected View getLoadingTargetView() {
        return recyclerView;
    }
}
