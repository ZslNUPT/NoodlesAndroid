package com.njupt.sniper.app.ui.activity;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.njupt.sniper.app.R;
import com.njupt.sniper.app.ui.fragment.NavigationFragment;
import com.njupt.sniper.app.ui.fragment.SecFragment;
import com.njupt.sniper.mylibrary.utils.ToastUtils;

public class HomeActivity extends BottomTabActivity {

    private boolean doubleBackToExitPressedOnce;

    private static final String TAG_NAVI = "tag_navi";
    private static final String TAG_RECRUIT = "tag_recruit";

    /**
     * 双击back键，退出应用
     */
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
        }

        doubleBackToExitPressedOnce = true;
        ToastUtils.getInstance().showToast(getResources().getString(R.string.double_click_exit));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    protected BottomTabActivity.TabInfo[] getTabs() {
        return new TabInfo[]{
                new TabInfo(NavigationFragment.class, R.drawable.tab_home, R.string.home_tab_1, TAG_NAVI),
                new TabInfo(SecFragment.class, R.drawable.tab_recruit, R.string.home_tab_2, TAG_RECRUIT)
        };
    }

    @Override
    protected View getTabIcon(TabInfo tabInfo) {
        View view = mInflater.inflate(com.njupt.sniper.mylibrary.R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(com.njupt.sniper.mylibrary.R.id.imageView);
        imageView.setImageResource(tabInfo.tabIcon);

        TextView textView = (TextView) view.findViewById(com.njupt.sniper.mylibrary.R.id.textview);
        textView.setText(tabInfo.tabText);

        return view;
    }
}
