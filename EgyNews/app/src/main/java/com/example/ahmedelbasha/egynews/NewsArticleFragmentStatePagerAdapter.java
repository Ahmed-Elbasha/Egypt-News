package com.example.ahmedelbasha.egynews;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class NewsArticleFragmentStatePagerAdapter extends FragmentStatePagerAdapter {


    public NewsArticleFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return  new BusinessNewsFragmentActivity.BusinessFragment();
        } else if (position == 1) {
            return new EntertainmentNewsFragmentActivity.EntertainmentFragment();
        } else if (position == 2) {
            return new PoliticsNewsFragmentActivity.PoliticsFragment();
        } else if (position == 3) {
            return  new SportsNewsFragmentActivity.SportsFragment();
        } else {
            return  new TechnologyNewsFragmentActivity.TechnologyFragment();
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 5;
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "آقتصاد";
        } else if (position == 1) {
            return "فن";
        } else if (position == 2) {
            return "سياسة";
        } else if (position == 3) {
            return "رياضة";
        } else {
            return "تكنولوجيا";
        }
    }
}
