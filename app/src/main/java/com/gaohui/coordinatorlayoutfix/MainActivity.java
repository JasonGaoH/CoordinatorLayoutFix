package com.gaohui.coordinatorlayoutfix;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gaohui.coordinatorlayoutfix.view.CoordinatorLayoutFix;
import com.gaohui.coordinatorlayoutfix.view.CustomBehavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> stringList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    private ArrayList<String> mDataList = new ArrayList<>();

    ViewPager viewPager;
    TabLayout tabs;
    RecyclerView parentRecyclerView;
    private String[] strArray = new String[]{"关注", "推荐", "视频", "直播", "图片", "段子", "精华", "热门"};

    @Override
    protected  synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        IndexPagerAdapter indexPagerAdapter = new IndexPagerAdapter(getSupportFragmentManager(),stringList,fragmentList);
        viewPager.setAdapter(indexPagerAdapter);

        tabs.setupWithViewPager(viewPager);
    }

    private void initViews() {
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);
        parentRecyclerView = findViewById(R.id.parent_recycler_view);

        CoordinatorLayoutFix coordinatorLayoutFix = findViewById(R.id.coordinator);
        final AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);
        coordinatorLayoutFix.setOnInterceptTouchListener(new CoordinatorLayoutFix.OnInterceptTouchListener() {
            @Override
            public void onIntercept() {
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)appBarLayout.getLayoutParams()).getBehavior();
                if(behavior instanceof CustomBehavior) {
                    //fix 解决与RecyclerView联合使用的回弹问题
                    if(!fragmentList.isEmpty() && viewPager.getCurrentItem() >= 0 && viewPager.getCurrentItem() < fragmentList.size()) {
                        ((ChildFragment)fragmentList.get(viewPager.getCurrentItem())).stopNestedScrolling();
                    }
                    //fix 解决动画抖动
                    ((CustomBehavior) behavior).stopFling();
                }

            }
        });

        initData();
    }

    private void initData() {
        stringList.addAll(Arrays.asList(strArray));

        fragmentList.add(ChildFragment.newInstance());
        fragmentList.add(ChildFragment.newInstance());
        fragmentList.add(ChildFragment.newInstance());
        fragmentList.add(ChildFragment.newInstance());
        fragmentList.add(ChildFragment.newInstance());
        fragmentList.add(ChildFragment.newInstance());

    }


    class IndexPagerAdapter extends FragmentPagerAdapter {
        private List<String> titleList;

        public IndexPagerAdapter(FragmentManager fm, List<String> titleList, List<Fragment> fragmentList) {
            super(fm);
            this.titleList = titleList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
