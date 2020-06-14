package com.dalzai.pendahealthv1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pagerAdapter extends FragmentPagerAdapter
{
    int myNumOfTabs;
    public pagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.myNumOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new homeFragment();
            case 1: return new chatFragment();
            case 2: return new notificationFragment();
            case 3: return new profileFragment();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return myNumOfTabs;
    }
}
