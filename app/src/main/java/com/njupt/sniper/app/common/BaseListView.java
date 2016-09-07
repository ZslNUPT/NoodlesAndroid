package com.njupt.sniper.app.common;

import java.util.List;

/**
 * author：Zsl
 * date：2016/9/7
 */
public interface BaseListView<E> extends BaseView {
    void configureRecyclerView(final List<E> items);
}
