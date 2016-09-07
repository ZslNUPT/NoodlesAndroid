package com.njupt.sniper.app.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.di.module.HttpMethodsModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * author：Zsl
 * date：2016/8/25
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    public HttpMethodsModule baseHttpMethods;

    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        MyApplication.getMainComponent().inject(this); // 应用注入
    }

    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
