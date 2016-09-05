package com.njupt.sniper.app.common.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.dagger.HttpMethodsModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * author：Zsl
 * date：2016/8/31
 */
public abstract class BasicFragment extends Fragment {

    @Inject
    public HttpMethodsModule baseHttpMethods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        MyApplication.getMainComponent().inject(this); // 应用注入

        return view;
    }

    protected abstract int  getLayoutId();

}
