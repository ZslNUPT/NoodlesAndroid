package com.njupt.sniper.testretrofit.fragment;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njupt.sniper.mylibrary.ui.fragment.ItemListFragment;
import com.njupt.sniper.testretrofit.adapter.AudioAdapter;
import com.njupt.sniper.testretrofit.entity.AudioEntity;

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
}
