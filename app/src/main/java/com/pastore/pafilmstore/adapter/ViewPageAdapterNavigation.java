package com.pastore.pafilmstore.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.pastore.pafilmstore.Fragment.Home_Main_Fragment;
import com.pastore.pafilmstore.Fragment.PopularFragment;
import com.pastore.pafilmstore.Fragment.SearchFragment;

public class ViewPageAdapterNavigation extends FragmentStatePagerAdapter {
    public ViewPageAdapterNavigation(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Home_Main_Fragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new PopularFragment();
            default:
                return new Home_Main_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
