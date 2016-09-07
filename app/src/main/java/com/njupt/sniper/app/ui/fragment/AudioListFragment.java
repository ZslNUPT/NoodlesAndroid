package com.njupt.sniper.app.ui.fragment;

import com.njupt.sniper.app.common.fragment.ItemListFragment;
import com.njupt.sniper.app.common.presenter.BaseListPresenter;
import com.njupt.sniper.app.model.entity.AudioEntity;
import com.njupt.sniper.app.ui.adapter.AudiooAdapter;
import com.njupt.sniper.app.ui.presenter.AudioListPresenter;

import java.util.List;

/**
 * author：Zsl
 * date：2016/9/7
 */
public class AudioListFragment extends ItemListFragment<AudioEntity,AudiooAdapter> {

    @Override
    protected AudiooAdapter createAdapter(List<AudioEntity> items) {
        return new AudiooAdapter(getActivity(),items);
    }

    @Override
    protected BaseListPresenter getChildPresenter() {
        return new AudioListPresenter(this,getActivity());
    }
}
