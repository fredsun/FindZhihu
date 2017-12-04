package com.findzhihu;

import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.findzhihu.backinterface.BackHandlerHelper;
import com.findzhihu.behavior.BottomBehavior;
import com.findzhihu.fragment.FragmentCommon;
import com.findzhihu.fragment.FragmentTransFirst;
import com.findzhihu.view.NonSwipeableViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomBehavior.onCanScrollCallback {

    String tag = "MainActivity";
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    TabLayout mainTab;
    boolean isBottomTabHide = false;
    NonSwipeableViewPager mainPager;
    boolean canScroll = true;
    BottomBehavior mBottomBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentList.add(FragmentTransFirst.newInstance());
        fragmentList.add(FragmentCommon.newInstance());
        fragmentList.add(FragmentCommon.newInstance());
        fragmentList.add(FragmentCommon.newInstance());
        mainPager = findViewById(R.id.main_pager);
        mainPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "0";
                    case 1:
                        return "1";
                    case 2:
                        return "2";
                    case 3:
                        return "3";
                    default:
                        return "0";

                }
            }
        });
        mainTab = findViewById(R.id.main_tab);
        mainTab.setupWithViewPager(mainPager);
        mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0 ){
                    canScroll = true;
                }else {
                    canScroll = false;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        mBottomBehavior = (BottomBehavior)((CoordinatorLayout.LayoutParams) mainTab.getLayoutParams()).getBehavior();
        mBottomBehavior.setOnCanScrollCallback(this);
    }

    public void bringViewPagerToFront(){
        mainTab.setVisibility(View.GONE);
        mBottomBehavior.showBottom();
    }

    public void bringViewPagerToBack(){
        mainTab.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean callbackCanScroll() {
        return canScroll;
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }

    }
}
