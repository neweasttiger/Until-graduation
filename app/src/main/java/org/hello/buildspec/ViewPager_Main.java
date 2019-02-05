package org.hello.buildspec;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


/**
 * Created by 성민 on 2018-03-30.
 * Revised by 동진 on 2019-01-30.
 */

public class ViewPager_Main extends AppCompatActivity {

    ViewPager mViewPager;
    private MenuItem currentPosition;

    TabLayout tabLayout;

    public static boolean ClickCount = true;
    long pressedTime;

    public static Activity viewpagerMain; //종료하려고

    public static Context mainContext; //event에서 갱신하려고

    BottomNavigationView bottomNavigationView; // 하단뷰
    public static int fragmentPosition; // 프레그먼트의 위치

    private BackPressCloseHandler backPressCloseHandler; // 뒤로 두번 누르면 종료

    Toolbar tb; // 툴바
    TextView tv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);

        backPressCloseHandler = new BackPressCloseHandler(this);

        fragmentPosition = 0; // 기본위치 = 0

        mainContext = this;

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationBar);

        viewpagerMain = ViewPager_Main.this;

        mViewPager = (ViewPager) findViewById(R.id.viewPagerScreen);

        tv = (TextView)findViewById(R.id.ActionBarTitle);
        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        // 탭관련 부분
        //tabLayout = (TabLayout) findViewById(R.id.TabLayout);
        //tabLayout.addTab(tabLayout.newTab().setText("Tab One"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab Two"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab Three"));
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        mViewPager.setCurrentItem(0);
                        return true;

                    case R.id.CoverLetter:
                        mViewPager.setCurrentItem(1);
                        return true;

                    case R.id.Grade:
                        mViewPager.setCurrentItem(2);
                        return true;

                    case R.id.BucketList:
                        mViewPager.setCurrentItem(3);
                        return true;

                    case R.id.Setting:
                        mViewPager.setCurrentItem(4);
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

        mViewPager.setAdapter(new pageAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(fragmentPosition);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                switch (position) {
                    case 0:
                        //setTitle("졸업까지");
                        tv.setText("졸업까지");
                        return;
                    case 1:
                        //setTitle("자소서");
                        tv.setText("자기소개서");
                        return;
                    case 2:
                        //setTitle("성적관리");
                        tv.setText("성적관리");
                        return;
                    case 3:
                        //setTitle("스펙 버킷리스트");
                        tv.setText("스펙 버킷리스트");
                        return;
                    case 4:
                        //setTitle("설정");
                        tv.setText("설정");
                        return;
                    default:
                        return;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setActionBarTitle(String title) {
        //getSupportActionBar().setTitle(title);
    }

    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }


}

