package org.hello.buildspec;

//import android.app.ActionBar;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.LinearLayout;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Home extends Fragment implements TabHost.OnTabChangeListener {

    Tab1 tab1;
    Tab2 tab2;
    Tab3 tab3;

    int cnt=0;
    private String htmlPageUrl = "https://www.yna.co.kr/"; //파싱할 홈페이지의 URL주소
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);
        View mView = inflater.inflate(R.layout.fragment_home, container, false);

        final ViewPager_Main activity = (ViewPager_Main) getActivity();

        textviewHtmlDocument = (TextView)mView.findViewById(R.id.textView);
        textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod()); //스크롤 가능한 텍스트뷰로 만들기

        Button htmlTitleButton = (Button)mView.findViewById(R.id.button);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println( (cnt+1) +"번째 파싱");
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
                cnt++;
            }
        });




        /*
        //3탭기능 구성
        //final TabLayout mTabLayout=(TabLayout)findViewById(R.id.TabLayout);
        final TabLayout mTabLayout=(TabLayout)view.findViewById(R.id.TabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("성적관리"));
        mTabLayout.addTab(mTabLayout.newTab().setText("스펙 버킷리스트"));
        mTabLayout.addTab(mTabLayout.newTab().setText("자기소개서"));

        //프레그먼트를 메니져로 보여줌
        activity.getSupportFragmentManager().beginTransaction().add(R.id.container,tab1).commit();

        //탭버튼을 클릭했을 때 프레그먼트 동작
        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //선택된 탭 번호 반환
                int position =tab.getPosition();

                Fragment selected = null;

                if(position == 0 ){
                    selected = tab1;
                }else if(position == 1 ){
                    selected = tab2;
                }else if(position == 2 ){
                    selected = tab3;
                }
                //선택된 프레그먼트로 바꿔줌
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
        */

        return layout;
    }

    class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document doc = Jsoup.connect(htmlPageUrl).get();

                //테스트1
                Elements titles= doc.select("div.news-con h1.tit-news");

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }

                //테스트2
                titles= doc.select("div.news-con h2.tit-news");

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }

                //테스트3
                titles= doc.select("li.section02 div.con h2.news-tl");

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }
                System.out.println("-------------------------------------------------------------");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            textviewHtmlDocument.setText(htmlContentInStringFormat);
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        return;
    }
}