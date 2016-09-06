package com.njupt.sniper.app.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.dagger.ActivityModule;
import com.njupt.sniper.app.dagger.HttpMethodsModule;

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

    //配合Dagger2使用 返回当前Activity的ActivityModule对象
    // ActivityModule生命周期与activity是绑定的
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}
