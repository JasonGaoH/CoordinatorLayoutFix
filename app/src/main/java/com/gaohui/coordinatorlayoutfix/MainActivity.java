package com.gaohui.coordinatorlayoutfix;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    protected void onCreate(Bundle savedInstanceState) {
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

//        parentRecyclerView.setAdapter(new RecyclerViewAdapter(mDataList));
//        parentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        for (int i =0;i<5;i++) {
//            mDataList.add("parent: " + i);
//        }
//        parentRecyclerView.getAdapter().notifyDataSetChanged();
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
