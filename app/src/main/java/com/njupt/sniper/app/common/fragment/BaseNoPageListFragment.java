package com.njupt.sniper.app.common.fragment;

import java.util.List;

/**
 * author：Zsl
 * date：2016/9/8
 */
public abstract class BaseNoPageListFragment<T> extends BaseListFragment<T> {
    @Override
    public void configureRecyclerView(List<T> items) {
        super.configureRecyclerView(items);
        hasNoMoreData();
    }

    @Override
    public void showRefreshFinish(List score) {
        super.showRefreshFinish(score);
        hasNoMoreData();
    }
}
