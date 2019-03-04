package org.hello.buildspec;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeTabAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public HomeTabAdapter(FragmentManager fm) {
            super(fm);
        }

        public void initFragment(Context mContext) {
            mFragments.add(new HomeTab1());
            mFragmentTitles.add("1");
            mFragments.add(new HomeTab2());
            mFragmentTitles.add("2");
            mFragments.add(new HomeTab3());
            mFragmentTitles.add("3");
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
}
