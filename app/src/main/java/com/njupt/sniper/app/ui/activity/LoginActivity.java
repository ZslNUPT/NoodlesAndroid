package com.njupt.sniper.app.ui.activity;

import com.njupt.sniper.app.common.activity.MultiFragmentActivity;
import com.njupt.sniper.app.ui.fragment.LoginFragment;

/**
 * author：Zsl
 * date：2016/8/25
 */
public class LoginActivity extends MultiFragmentActivity  {

    @Override
    protected void initFragments() {
        addFragment(new LoginFragment(),"LoginFragment");
    }

}
