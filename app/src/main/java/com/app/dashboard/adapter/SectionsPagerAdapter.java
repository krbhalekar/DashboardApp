package com.app.dashboard.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.dashboard.activity.HomeActivity;
import com.app.dashboard.fragment.LineGraphFragment;
import com.app.dashboard.fragment.PieChartFragment;
import com.app.dashboard.fragment.WelcomeFragment;

/**
 * Created by khushalb on 23/10/19.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new WelcomeFragment();
            case 1:
                return new PieChartFragment();
            case 2:
                return new LineGraphFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
