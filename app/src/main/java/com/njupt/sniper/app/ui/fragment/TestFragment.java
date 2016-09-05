package com.njupt.sniper.app.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.njupt.sniper.app.model.entity.AudioEntity;
import com.njupt.sniper.app.ui.adapter.AudioAdapter;
import com.njupt.sniper.app.common.fragment.ItemListFragment;

import java.util.List;

/**
 * @author chenshenglong
 * @date 16-8-30
 */
public class TestFragment extends ItemListFragment<AudioEntity, AudioAdapter> {

    @Override
    protected AudioAdapter createAdapter(List<AudioEntity> items) {
        return null;
    }

    @Override
    public Loader<List<AudioEntity>> onCreateLoader(int id, Bundle args) {

        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<AudioEntity>> loader, List<AudioEntity> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<AudioEntity>> loader) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
