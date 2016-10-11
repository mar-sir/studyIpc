package com.example.designer.studyipc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by huangcl on 2016/10/11.
 */
public class ViewAdapter extends FragmentPagerAdapter {
    private ArrayList<String>titles;
    private ArrayList<Fragment>fragments;


    public ViewAdapter(FragmentManager fm,ArrayList<String>titles,ArrayList<Fragment>fragments) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
        if (titles.size()!=fragments.size()) throw new IllegalArgumentException("Stub!");
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
