package com.njupt.sniper.app.common.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.njupt.sniper.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author：Zsl
 * date：2016/3/24
 */
public abstract class MultiFragmentActivity extends BaseActivity {
    public List<Fragment> fragments = new ArrayList<>();

    private int currentPosition;

    public static boolean isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        initFragments();

        if (fragments.size() > 0)

            showFragmentInPosition(0);

    }

    /**
     * addFragment();
     */
    protected abstract void initFragments();

    protected int getContainerViewId() {
        return R.id.fragment_container;
    }

    public int getLayoutId(){
        return R.layout.activity_multi_fragment;
    };

    public void addFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();

        Fragment fragment1 = fm.findFragmentByTag(tag);
        if (fragment1 == null) {
            fragment1 = fragment;
        }
        if (!fragment1.isAdded()) {
            tx.add(getContainerViewId(), fragment1, tag);
        }

        if(!fragments.contains(fragment1))
            fragments.add(fragment1);

        tx.commitAllowingStateLoss();
    }

    public void showFragmentInPosition(int position) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == position)
                tx.show(fragments.get(i));
            else
                tx.hide(fragments.get(i));
        }
        tx.commitAllowingStateLoss();
        currentPosition = position;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    //返回键在不同层次页面下操作处理
    @Override
    public void onBackPressed() {
        isBack = true;
        if (currentPosition > 0) {
            showFragmentInPosition(0);
        } else {
            finish();
        }
    }

}
