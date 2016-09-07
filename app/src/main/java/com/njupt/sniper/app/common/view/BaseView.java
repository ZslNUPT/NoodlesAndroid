/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.njupt.sniper.app.common.view;


import java.util.List;

public interface BaseView {
    void showLoading();

    void showEmptyView(String msg);

    void hideLoading();

    void showNetError();

    void hasNoMoreData();

    void loadMoreFinish(List dates);

    void showRefreshFinish(List score);

    void showToastError();
}
