package com.njupt.sniper.mylibrary.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author chenshenglong
 * @date 16-8-30
 */
public abstract class HeaderFooterRecyclerAdapter<S extends Object> extends RecyclerView.Adapter {

    //数据集合
    private List<S> mDatas;

    private View mHeaderView;

    private View mFooterView;

    //没有footer 和 header
    public final static int TYPE_NORMAL = 0;

    public final static int TYPE_HEADER = 1;

    public final static int TYPE_FOOTER = 2;

    protected abstract RecyclerView.ViewHolder getRecyclerViewHolder(View view);

    protected abstract View getItemLayout();

    protected abstract void setDataToView(RecyclerView.ViewHolder hold, List<S> datas);

    public View getmHeaderView() {
        return mHeaderView;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);
    }

    public View getmFooterView() {
        return mFooterView;
    }

    public void setmFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
        notifyItemInserted(getItemCount() - 1);
    }

    public HeaderFooterRecyclerAdapter(List<S> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return getRecyclerViewHolder(mHeaderView);
        }

        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return getRecyclerViewHolder(mFooterView);
        }

        return getRecyclerViewHolder(getItemLayout());

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
//             ((ListHolder) holder).tv.setText(mDatas.get(position-1));
            setDataToView(holder, mDatas);
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return mDatas.size();
        } else if (mHeaderView == null && mFooterView != null) {
            return mDatas.size() + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return mDatas.size() + 1;
        } else {
            return mDatas.size() + 2;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null)
            return TYPE_NORMAL;

        if (position == 0)
            return TYPE_HEADER;

        if (position == getItemCount() - 1)
            return TYPE_FOOTER;

        return TYPE_NORMAL;
    }


    abstract class RecyclerViewHolder extends RecyclerView.ViewHolder {
        protected int viewType;

        public RecyclerViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            //如果是headerview或者是footerview,直接返回
            if (itemView == mHeaderView) {
                return;
            }
            if (itemView == mFooterView) {
                return;
            }
            initViews();
        }

        protected abstract void initViews();
    }

}
