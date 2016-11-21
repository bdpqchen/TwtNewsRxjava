package com.example.cdc.twtnewsrxjava.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdc on 16-11-13.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private final String mTabTitles[] = new String[]{"天大要闻", "校园公告", "社团风采", "院系动态", "视点观察"};

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }

}
