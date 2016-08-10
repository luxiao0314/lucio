package com.ui.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.base.ex.ExBaseActivity;
import com.ui.adapter.MainPagerAdapter;
import com.ui.main.R;
import com.ui.model.MainModel;
import com.ui.presenter.MainPresenter;

import butterknife.Bind;

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 16/8/10 22:53
 * @Version v2.6.0
 */
public class MainActivity extends ExBaseActivity<MainPresenter,MainModel> {
    @Bind(R.id.main_viewpager)
    ViewPager mMainViewpager;
    @Bind(R.id.main_tablayout)
    TabLayout mMainTablayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mMainViewpager.setAdapter(mainPagerAdapter);
        mMainTablayout.setupWithViewPager(mMainViewpager);
        mMainTablayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
