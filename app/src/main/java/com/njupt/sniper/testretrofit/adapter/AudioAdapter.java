package com.njupt.sniper.testretrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.njupt.sniper.mylibrary.ui.adapter.HeaderFooterRecyclerAdapter;
import com.njupt.sniper.testretrofit.entity.AudioEntity;

import java.util.List;

/**
 * @author chenshenglong
 * @date 16-8-30
 */
public class AudioAdapter extends HeaderFooterRecyclerAdapter<AudioEntity> {

    public AudioAdapter(List<AudioEntity> mDatas) {
        super(mDatas);
    }

    @Override
    protected RecyclerView.ViewHolder getRecyclerViewHolder(View view) {
        return null;
    }

    @Override
    protected View getItemLayout() {
        return null;
    }

    @Override
    protected void setDataToView(RecyclerView.ViewHolder hold, List<AudioEntity> datas) {

    }
}
