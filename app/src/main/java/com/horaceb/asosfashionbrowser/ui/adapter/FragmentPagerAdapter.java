package com.horaceb.asosfashionbrowser.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * A simple adapter for fragments in a ViewPager
 */
public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    @NonNull
    private final List<Fragment> fragments;

    public FragmentPagerAdapter(FragmentManager fm, @NonNull final List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
