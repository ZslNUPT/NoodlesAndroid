package com.njupt.sniper.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

import com.njupt.sniper.mylibrary.R;
import com.njupt.sniper.app.common.activity.BasicActivity;

import butterknife.Bind;

/**
 * author：Zsl
 * date：2016/8/31
 */
public abstract class BottomTabActivity extends BasicActivity {
    @Bind(R.id.tab_host)
    FragmentTabHost mTabHost;

    @Bind(R.id.th_tabs)
    View tabContainer;

    protected LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        //底部导航栏
        initTabs();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bottom_tab;
    }

    protected abstract TabInfo[] getTabs();

    private void initTabs() {
        //实例化TabHost对象，得到TabHost
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);
        mTabHost.getTabWidget().setDividerDrawable(null);

        mInflater = LayoutInflater.from(this);

        //得到fragment的个数
        for (TabInfo tabInfo : getTabs()) {
            TabHost.TabSpec tabSpec = mTabHost
                    .newTabSpec(tabInfo.tag)
                    .setIndicator(getTabIcon(tabInfo));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, tabInfo.fClz, null);
        }

        tabContainer.setVisibility(View.VISIBLE);

    }

    protected abstract View getTabIcon(TabInfo tabInfo);

    //设置当前界面位于第几个fragment
    public void setCurrentFragment(int i) {
        mTabHost.setCurrentTab(i);
    }

    public class TabInfo {
        public Class<? extends Fragment> fClz;
        public int tabIcon;
        public int tabText;
        public String tag;

        public TabInfo(Class<? extends Fragment> fClz, int tabIcon, int tabText, String tag) {
            this.fClz = fClz;
            this.tabIcon = tabIcon;
            this.tabText = tabText;
            this.tag = tag;
        }
    }
}
