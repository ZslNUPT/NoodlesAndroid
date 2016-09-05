package com.njupt.sniper.app.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author chenshenglong
 * @date 16-8-30
 */
public abstract class HeaderFooterRecyclerAdapter<E extends Object, S extends RecyclerView.Adapter> extends RecyclerView.Adapter {

    //数据集合
    private List<E> mDatas;

    private View mHeaderView;

    private View mFooterView;

    private final S wrapped;


    //没有footer 和 header
    public final static int TYPE_NORMAL = 0;

    public final static int TYPE_HEADER = 1;

    public final static int TYPE_FOOTER = 2;

    protected abstract RecyclerView.ViewHolder initRecyclerViewHolder(View itemView, int viewType);

    protected abstract View getItemLayout(ViewGroup parent);

    protected abstract void setDataToView(RecyclerView.ViewHolder viewHolder, List<E> datas, int position);

    public View getmHeaderView() {
        return mHeaderView;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        wrapped.notifyItemInserted(0);
    }

    public View getmFooterView() {
        return mFooterView;
    }

    public void setmFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
        wrapped.notifyItemInserted(getItemCount() - 1);
    }

    public HeaderFooterRecyclerAdapter(List<E> mDatas, S adapter) {
        this.mDatas = mDatas;
        this.wrapped = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return initRecyclerViewHolder(mHeaderView, viewType);
        }

        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return initRecyclerViewHolder(mFooterView, viewType);
        }

        return initRecyclerViewHolder(getItemLayout(parent), viewType);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
//             ((ListHolder) holder).tv.setText(mDatas.get(position-1));
            setDataToView(holder, mDatas, position - 1);
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


    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {
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
        }

    }

}
