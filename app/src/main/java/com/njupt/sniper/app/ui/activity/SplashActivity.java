package com.njupt.sniper.app.ui.activity;

import com.njupt.sniper.app.common.activity.MultiFragmentActivity;
import com.njupt.sniper.app.ui.fragment.SplashFragment;

/**
 * author：Zsl
 * date：2016/9/8
 */
public class SplashActivity extends MultiFragmentActivity {
    @Override
    protected void initFragments() {
        addFragment(new SplashFragment(), "SplashFragment");
    }

}
