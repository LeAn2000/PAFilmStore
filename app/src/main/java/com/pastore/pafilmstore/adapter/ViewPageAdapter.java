package com.pastore.pafilmstore.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.pastore.pafilmstore.Fragment.Tablayout.UpcomingFragment;
import com.pastore.pafilmstore.Fragment.Tablayout.ChildPopularFragment;
import com.pastore.pafilmstore.Fragment.Tablayout.TopratedFragment;


public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new ChildPopularFragment();
            case 1:
                return new UpcomingFragment();
            case 2:
                return new TopratedFragment();
            default:
                return new ChildPopularFragment();

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title= "";
        switch (position)
        {
            case 0:
                title = "Popular";
                break;
            case 1:
                title = "Upcoming";
                break;
            case 2:
                title = "Top Rated";
                break;
        }
        return title;
    }
}
