package com.example.cdc.twtnewsrxjava.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.example.cdc.twtnewsrxjava.R;
import com.example.cdc.twtnewsrxjava.adapter.ViewPagerAdapter;
import com.example.cdc.twtnewsrxjava.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by chen on 16-9-1.
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.toolBar)
    Toolbar mToolBar;
    @InjectView(R.id.tablayout)
    TabLayout mTabLayout;
    @InjectView(R.id.viewpager)
    ViewPager mViewPager;
    @InjectView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        for(int i = 1; i <= 5; i++) {
            viewPagerAdapter.addFragment(NewsFragment.tabIndex(i));
        }
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void actionStart(Context context) {

    }

    @Override
    protected int getStatusBasColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected Toolbar getToolbar(){
        mToolBar.setTitle(getString(R.string.app_name));
        return mToolBar;
    }

}
