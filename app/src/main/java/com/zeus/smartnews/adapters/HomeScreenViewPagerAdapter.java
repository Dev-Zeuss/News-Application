package com.zeus.smartnews.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zeus.smartnews.fragments.BusinessFragment;
import com.zeus.smartnews.fragments.EntertainmentFragment;
import com.zeus.smartnews.fragments.GeneralFragment;
import com.zeus.smartnews.fragments.HealthFragment;
import com.zeus.smartnews.fragments.ScienceFragment;
import com.zeus.smartnews.fragments.SportFragment;
import com.zeus.smartnews.fragments.TechFragment;

import org.jetbrains.annotations.NotNull;

public class HomeScreenViewPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public HomeScreenViewPagerAdapter( @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new GeneralFragment();
            case 1:
                return new BusinessFragment();
            case 2:
                return new ScienceFragment();
            case 3:
                return new HealthFragment();
            case 4:
                return new SportFragment();
            case 5:
                return new TechFragment();
            case 6:
                return new EntertainmentFragment();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
