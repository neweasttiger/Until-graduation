package org.hello.buildspec;

//import android.app.ActionBar;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;
import android.widget.TextView;


public class Home extends Fragment implements TabHost.OnTabChangeListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        ViewPager_Main activity = (ViewPager_Main) getActivity();

        return layout;
    }

    @Override
    public void onTabChanged(String tabId) {
        return;
    }
}