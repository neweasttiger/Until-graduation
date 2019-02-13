package org.hello.buildspec;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import static android.app.Activity.RESULT_OK;

public class BucketList extends Fragment {

    int i = 0;
    int j = 500;
    int k = 1000;
    int s = 1500;
    float x = 0, y = 0;
    Vector languageId = new Vector();
    TextView text2;
    EditText name;
    EditText score;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
    //public View onCreateView(ScrollView inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScrollView mScrollView = (ScrollView) inflater.inflate(R.layout.fragment_bucketlist, container, false);
        //LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_bucketlist, container, false);
        final View mView = inflater.inflate(R.layout.fragment_bucketlist, container, false);

        //****  어학  ****//
        text2 = (TextView) mView.findViewById(R.id.dateselect2);
        name = (EditText) mView.findViewById(R.id.examname);
        score = (EditText) mView.findViewById(R.id.scoregrade);
        LinearLayout ss = (LinearLayout) mView.findViewById(R.id.lanorigin);
        TextView add = (TextView) mView.findViewById(R.id.addbutton);

        ss.setId(s);
        text2.setId(i);
        name.setId(j);
        score.setId(k);
        languageId.add(i);
        i++;
        j++;
        k++;
        s++;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "add", Toast.LENGTH_SHORT).show();
                LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.language);
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.language, linearLayout, true);

                TextView newtext2 = (TextView) mView.findViewById(R.id.newdateselect2);
                EditText newexam2 = (EditText) mView.findViewById(R.id.newexamname);
                EditText newscore2 = (EditText) mView.findViewById(R.id.newscoregrade);
                LinearLayout ss = (LinearLayout) mView.findViewById(R.id.newlanorigin);

                ss.setId(s);
                newtext2.setId(i);
                languageId.add(i);
                newexam2.setId(j);
                newscore2.setId(k);
                i++;
                j++;
                k++;
                s++;
                newtext2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String numStr2 = String.valueOf(v.getId());
                        Toast.makeText(getActivity().getApplicationContext(), numStr2, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), GetCalendar.class);
                        intent.putExtra("key", "value");
                        startActivityForResult(intent, v.getId());
                    }

                });


            }
        });

        /*
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), GetCalendar.class);
                intent.putExtra("key", "value");
                startActivityForResult(intent, 100);
            }

        });
        */

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), GetCalendar.class);
                intent.putExtra("key", "value");
                startActivityForResult(intent, v.getId());
            }

        });

        return mScrollView;

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
     public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //final View mView = inflater.inflate(R.layout.fragment_bucketlist, container, false);

        if (resultCode != RESULT_OK)
            return;

        if (requestCode == 100) {
            String result = data.getStringExtra("key");
            String dday = data.getStringExtra("day");
            String rd = dday + "\n" + result;
            TextView text = (TextView) getActivity().findViewById(R.id.dateselect1);
            text.setText(rd);

            Toast.makeText(getActivity().getApplicationContext(), dday, Toast.LENGTH_SHORT).show();
        }

        if (languageId.contains(requestCode)) {

            TextView text2 = (TextView) getActivity().findViewById(requestCode);
            EditText name = (EditText) getActivity().findViewById(requestCode + 500);
            EditText score = (EditText) getActivity().findViewById(requestCode + 1000);

            // ****************** d day 생성
            String result2 = data.getStringExtra("key");
            String dday2 = data.getStringExtra("day");
            String s = result2.substring(2);
            String rd2 = dday2 + "\n" + s;

            SpannableString srd2 = new SpannableString(rd2);
            int start = rd2.indexOf("\n");
            int end = rd2.length();
            srd2.setSpan(new ForegroundColorSpan(Color.parseColor("#4dabff")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            srd2.setSpan(new RelativeSizeSpan(1.2f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            // *************************************************

            text2.setText(srd2);
            text2.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.TOP | Gravity.END;
            lp.weight = 4;
            text2.setLayoutParams(lp);
            text2.setClickable(false);

            name.setClickable(false);
            name.setFocusable(false);
            score.setClickable(false);
            score.setFocusable(false);

            //********************** 슬라이드 이벤트
            LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(requestCode + 1500);

            linearLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override

                public boolean onTouch(View v, MotionEvent event) {
                    final int layoutId = v.getId();
                    final int MOVE = 10;
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN: {
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                            x = event.getRawX();
                            y = event.getRawY();
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                            float diffxx = x - event.getRawX();
                            float diffyy = y - event.getRawY();
                            if (Math.abs(diffxx) > Math.abs(diffyy)) {
                                if (diffxx < MOVE) {
                                    Toast.makeText(getActivity().getApplicationContext(), "오른쪽때짐", Toast.LENGTH_SHORT).show();

                                    final LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(layoutId);
                                    linearLayout.setVisibility(View.GONE);
                                    LinearLayout linearLayouts = (LinearLayout) getActivity().findViewById(R.id.language);
                                    LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.MATCH_PARENT,
                                            LinearLayout.LayoutParams.MATCH_PARENT);
                                    LayoutInflater comdeleinflate = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    LinearLayout comdelelayout = (LinearLayout) comdeleinflate.inflate(R.layout.comdele, null);

                                    final int comdeleId = layoutId + 500;
                                    comdelelayout.setId(comdeleId);

                                    linearLayouts.addView(comdelelayout, paramlinear);
                                    TextView dele = (TextView) getActivity().findViewById(R.id.dele);
                                    dele.setId(comdeleId + 10);
                                    TextView com = (TextView) getActivity().findViewById(R.id.com);
                                    com.setId(comdeleId + 50);
                                    com.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            LinearLayout linearLayouts = (LinearLayout) getActivity().findViewById(comdeleId);
                                            linearLayouts.setVisibility(View.GONE);
                                            LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(layoutId);
                                            linearLayout.setBackgroundResource(R.drawable.completeborderbottom);
                                            linearLayout.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    dele.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            LinearLayout linearLayouts = (LinearLayout) getActivity().findViewById(comdeleId);
                                            linearLayouts.removeAllViews();
                                        }
                                    });
                                    break;
                                }
                            }

                        }
                    }
                    return true;
                }

            });

        }
    }

    /////
    public int getgrade(String s, int score) {

        return i;
    }

    /*
    //빈공간 터치시 키보드 숨김
    public void keyboardControl() {
        mScrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                touch_hide.hideSoftInputFromWindow(layout.getWindowToken(), 0);
            }
        });
    }
    */

}
