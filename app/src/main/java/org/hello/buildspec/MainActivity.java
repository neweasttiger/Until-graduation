package org.hello.buildspec;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.ToolBar);
        //setSupportActionBar(toolbar);
        // Get the ActionBar here to configure the way it behaves.
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        //actionBar.setDisplayShowTitleEnabled(false);

        //현재 액션바 완벽하게 좌우 채워지지 않은 상태
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.layout_actionbar);
        //getSupportActionBar().setTitle("hello");


        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //actionBar.setCustomView(R.layout.layout_actionbar);
        //actionBar.setElevation(0);

        //ActionBar actionBar = getSupportActionBar();
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        //actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );


    }
}

/*
 // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.

        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.layout_actionbar, null);

        actionBar.setCustomView(actionbar);

        // 액션바에 백그라운드 색상을 아래처럼 입힐 수 있습니다.
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));
 */