package org.hello.buildspec;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

/**
 * Created by 성민 on 2018-03-30.
 * Revised by 동진 on 2019-01-30.
 */

public class ViewPager_Main extends FragmentActivity {

    ViewPager viewPager;
    private MenuItem currentPosition;

    public static boolean ClickCount = true;
    long pressedTime;

    public static Activity viewpagerMain; //종료하려고

    public static Context mainContext; //event에서 갱신하려고

    BottomNavigationView bottomNavigationView; // 하단뷰
    public static int fragmentPosition; // 프레그먼트의 위치

    private BackPressCloseHandler backPressCloseHandler; // 뒤로 두번 누르면 종료

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);

        backPressCloseHandler = new BackPressCloseHandler(this);

        fragmentPosition = 0; // 기본위치 = 0

        mainContext = this;

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationBar);

        viewpagerMain = ViewPager_Main.this;

        viewPager = (ViewPager) findViewById(R.id.viewPagerScreen);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        viewPager.setCurrentItem(0);
                        return true;

                    case R.id.CoverLetter:
                        viewPager.setCurrentItem(1);
                        return true;

                    case R.id.Grade:
                        viewPager.setCurrentItem(2);
                        return true;

                    case R.id.BucketList:
                        viewPager.setCurrentItem(3);
                        return true;

                    case R.id.Setting:
                        viewPager.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });

    }

    private class pageAdapter extends FragmentPagerAdapter {
        public pageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Home();
                case 1:
                    return new CoverLetter();
                case 2:
                    return new Grade();
                case 3:
                    return new BucketList();
                case 4:
                    return new Setting();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        viewPager.setAdapter(new pageAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(fragmentPosition);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (currentPosition != null) {
                    currentPosition.setChecked(false);
                }
                currentPosition = bottomNavigationView.getMenu().getItem(position);
                currentPosition.setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
