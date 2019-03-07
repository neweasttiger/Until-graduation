package org.hello.buildspec;

//import android.app.ActionBar;
import android.os.AsyncTask;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Home extends Fragment implements TabHost.OnTabChangeListener {

    int cnt=0;
    private String htmlPageUrl = "https://sc.inu.ac.kr/inumportal/main/noti/list_all?page_num=1&board_id=0"; //파싱할 홈페이지의 URL주소
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat="";
    TextView tv1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);
        final View mView = inflater.inflate(R.layout.fragment_home, container, false);
        final ViewPager_Main activity = (ViewPager_Main) getActivity();
        tv1 = (TextView)mView.findViewById(R.id.Notice_Content1);

        /* 인터넷 연결관련 */
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
        //System.out.println("으아악"+textviewHtmlDocument.getText());

        //TabLayout mTabLayout = (TabLayout)mView.findViewById(R.id.isTabLayout);
        HomeTabAdapter mHomeTabAdapter = new HomeTabAdapter(getFragmentManager());
        /*
        ViewPager mViewPager = (ViewPager)mView.findViewById(R.id.Ugly);
        mViewPager.setAdapter(mHomeTabAdapter);

        FragmentTabHost host = (FragmentTabHost) mView.findViewById(R.id.TabHost);
        host.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);


        TabHost.TabSpec spec = host.newTabSpec("Tab11");
        spec.setIndicator("성적관리");
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);


        spec = host.newTabSpec("Tab22");
        spec.setIndicator("스펙버킷리스트");
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec = host.newTabSpec("Tab33");
        spec.setIndicator("자기소개서");
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

        */

        TabLayout mTabLayout=(TabLayout)mView.findViewById(R.id.HomeTabLayout);

        //프레그먼트를 메니져로 보여줌
        //activity.getSupportFragmentManager().beginTransaction().add(R.id.container,tab1).commit();

        //탭버튼을 클릭했을 때 프레그먼트 동작
        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //선택된 탭 번호 반환
                int position = tab.getPosition();
                changeView(position) ;

                // 프래그먼트 전환인 경우 사용
                // Fragment selected = null;

                /*
                LinearLayout homeTab_content1 = (LinearLayout)mView.findViewById(R.id.HomeTab_Content1);
                RelativeLayout homeTab_content2 = (RelativeLayout)mView.findViewById(R.id.HomeTab_Content2);
                RelativeLayout homeTab_content3 = (RelativeLayout)mView.findViewById(R.id.HomeTab_Content3);
                */

                /*
                switch (position) {
                    case 0 :
                        homeTab_content1.setVisibility(View.VISIBLE);
                        homeTab_content2.setVisibility(View.INVISIBLE);
                        homeTab_content3.setVisibility(View.INVISIBLE);
                        break;
                    case 1 :
                        homeTab_content1.setVisibility(View.INVISIBLE);
                        homeTab_content2.setVisibility(View.VISIBLE);
                        homeTab_content1.setVisibility(View.INVISIBLE);
                        break;
                    case 2 :
                        homeTab_content1.setVisibility(View.INVISIBLE);
                        homeTab_content2.setVisibility(View.INVISIBLE);
                        homeTab_content3.setVisibility(View.VISIBLE);
                        break;
                    default:
                        homeTab_content1.setVisibility(View.INVISIBLE);
                        homeTab_content2.setVisibility(View.INVISIBLE);
                        homeTab_content3.setVisibility(View.INVISIBLE);

                }
                */

                /*
                if(position == 0 ){
                    homeTab_content1.setVisibility(View.INVISIBLE);
                    homeTab_content2.setVisibility(View.VISIBLE);
                    homeTab_content1.setVisibility(View.INVISIBLE);
                    // selected = tab1; 프래그먼트인 경우
                }else if(position == 1 ){
                    homeTab_content1.setVisibility(View.INVISIBLE);
                    homeTab_content2.setVisibility(View.VISIBLE);
                    homeTab_content1.setVisibility(View.INVISIBLE);
                    // selected = tab2; 프래그먼트인 경우
                }else if(position == 2 ){
                    homeTab_content1.setVisibility(View.INVISIBLE);
                    homeTab_content2.setVisibility(View.INVISIBLE);
                    homeTab_content3.setVisibility(View.VISIBLE);
                    // selected = tab3; 프래그먼트인 경우
                }
                */


                // 선택된 프래그먼트로 바꿔줌, 이 앱에서는 뷰전환 할것이므로 사용X
                // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }

            private void changeView(int index) {
                //LinearLayout homeTab_content1 = (LinearLayout)mView.findViewById(R.id.HomeTab_Content1);
                RelativeLayout homeTab_content1 = (RelativeLayout)mView.findViewById(R.id.HomeTab_Content1);
                RelativeLayout homeTab_content2 = (RelativeLayout)mView.findViewById(R.id.HomeTab_Content2);
                RelativeLayout homeTab_content3 = (RelativeLayout)mView.findViewById(R.id.HomeTab_Content3);

                switch (index) {
                    case 0 :
                        homeTab_content1.setVisibility(View.VISIBLE);
                        homeTab_content2.setVisibility(View.INVISIBLE);
                        homeTab_content3.setVisibility(View.INVISIBLE);
                        break;
                    case 1 :
                        homeTab_content1.setVisibility(View.INVISIBLE);
                        homeTab_content2.setVisibility(View.VISIBLE);
                        homeTab_content3.setVisibility(View.INVISIBLE);
                        break;
                    case 2 :
                        homeTab_content1.setVisibility(View.INVISIBLE);
                        homeTab_content2.setVisibility(View.INVISIBLE);
                        homeTab_content3.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });


        return mView;
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
                //Elements titles= doc.select("div.news-con h1.tit-news");
                Elements titles= doc.select("div.cont");

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
            tv1.setText(htmlContentInStringFormat);
            System.out.println("으아악"+textviewHtmlDocument.getText());
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        return;
    }

    /*

    // * @param txt<br/>
    // *  @param len : 생략시 기본값 20<br/>
    // *  @param lastTxt : 생략시 기본값 "..."<br/>
    // *  @returns 결과값
    // * <br/>
    // * <br/>
    // * 특정 글자수가 넘어가면 넘어가는 글자는 자르고 마지막에 대체문자 처리<br/>
    // *  ex) 가나다라마바사 -> textLengthOverCut('가나다라마바사', '5', '...') : 가나다라마...<br/>
    //
    void textLengthOverCut(String txt, int len, char lastTxt) {
        if (len == "" || len == null) { // 기본값
            len = 20;
        }
        if (lastTxt == "" || lastTxt == null) { // 기본값
            lastTxt = "...";
        }
        if (txt.length > len) {
            txt = txt.substr(0, len) + lastTxt;
        }
        return txt;
    }
    */

}