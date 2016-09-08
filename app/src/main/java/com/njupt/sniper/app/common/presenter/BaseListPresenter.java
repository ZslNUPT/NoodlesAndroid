package com.njupt.sniper.app.common.presenter;

import android.app.Activity;

import com.njupt.sniper.app.common.view.BaseListView;

import java.util.List;

/**
 * author：Zsl
 * date：2016/9/7
 */
public abstract class BaseListPresenter<T> extends BasePresenter<List<T>> {

    public BaseListPresenter(BaseListView<T> view, Activity activity) {
        mView = view;
        mActivity = activity;
    }

    @Override
    protected void onAllSuccess(List<T> t) {

        if (null != t && t.size() > 0) {
            if (mode == RequestMode.FIRST) {
                ((BaseListView<T>)mView).configureRecyclerView(t);
            } else if (mode == RequestMode.LOAD_MORE) {
                mView.loadMoreFinish(t);
            } else if (mode == RequestMode.REFRESH) {
                mView.showRefreshFinish(t);
            }
        } else {
            if (mode == RequestMode.LOAD_MORE) {
                mView.hasNoMoreData();
            } else {
                mView.showEmptyView(null);
            }
        }
    }

    @Override
    protected void onFail() {
        if(mode == RequestMode.FIRST){
            mView.showNetError();
        }else{
            mView.showToastError();
        }
    }

}
