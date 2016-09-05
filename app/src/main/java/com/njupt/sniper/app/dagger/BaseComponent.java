package com.njupt.sniper.app.dagger;

import com.njupt.sniper.app.MyApplication;
import com.njupt.sniper.app.common.activity.BasicActivity;
import com.njupt.sniper.app.common.fragment.BasicFragment;

/**
 * author：Zsl
 * date：2016/9/5
 */
public interface BaseComponent {
    void inject(MyApplication myApplication);

    void inject(BasicActivity basicActivity);

    void inject(BasicFragment basicFragment);
}
