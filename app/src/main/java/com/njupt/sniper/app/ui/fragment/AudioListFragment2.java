package com.njupt.sniper.app.ui.fragment;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.common.adapter.BaseViewHolder;
import com.njupt.sniper.app.common.fragment.BaseNoPageListFragment;
import com.njupt.sniper.app.common.presenter.BaseListPresenter;
import com.njupt.sniper.app.model.entity.AudioEntity;
import com.njupt.sniper.app.ui.presenter.AudioListPresenter2;

/**
 * author：Zsl
 * date：2016/9/8
 */
public class AudioListFragment2 extends BaseNoPageListFragment<AudioEntity> {

    @Override
    protected BaseListPresenter getChildPresenter() {
        return new AudioListPresenter2(this, getActivity());
    }

    @Override
    protected void fitDates(BaseViewHolder helper, AudioEntity item) {
        helper.setText(R.id.navi_audio_title, item.getTitle())
                .setImageUrl(R.id.navi_audio_img, getActivity(), item.getImg());
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_recycler_audio;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        setToolbar(true, "音频列表");
    }

}